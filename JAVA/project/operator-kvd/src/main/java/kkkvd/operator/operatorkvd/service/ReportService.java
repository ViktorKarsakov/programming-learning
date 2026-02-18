package kkkvd.operator.operatorkvd.service;

import kkkvd.operator.operatorkvd.dto.ReportRequest;
import kkkvd.operator.operatorkvd.entities.DetectionCase;
import kkkvd.operator.operatorkvd.entities.DiagnosisGroup;
import kkkvd.operator.operatorkvd.entities.Population;
import kkkvd.operator.operatorkvd.entities.State;
import kkkvd.operator.operatorkvd.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final DetectionCaseRepository detectionCaseRepository;
    private final DiagnosisRepository diagnosisRepository;
    private final DiagnosisGroupRepository diagnosisGroupRepository;
    private final StateRepository stateRepository;
    private final PopulationRepository populationRepository;

    private static final String GENDER_MALE = "MALE";
    private static final String GENDER_FEMALE = "FEMALE";
    private static final String CITIZEN_TYPE_RURAL = "RURAL";
    //Группа районов города Красноярска
    private static final String STATE_GROUP_CITY = "KRASNOYARSK";
    private static final Set<String> DDU_CODES = Set.of("KINDERGARTEN_ORG", "KINDERGARTEN_UNORG");
    private static final String[] INDICATORS_GROUP_CODES = {
            "SYPHILIS", "GONORRHEA", "SCABIES", "TRICHOMONIASIS",
            "CHLAMYDIA", "HERPES", "CONDYLOMAS", "MYCOSES", "MICROSPORIA"
    };
    private static final String SYPHILIS_CODE = "SYPHILIS";
    private static final String[] PER100K_GROUP_CODES = {
            "SYPHILIS", "GONORRHEA", "SCABIES", "MICROSPORIA", "MYCOSES"
    };
    private static final Set<String> GROUPS_WITH_AGE_SUBSECTIONS = Set.of(
            "MYCOSES", "MICROSPORIA"
    );
    private static final Set<String> GROUPS_WITH_PROFILE_SECTION = Set.of(
            "SYPHILIS", "CONDYLOMAS", "MICROSPORIA", "TRICHOMONIASIS"
    );

    private List<Long> resolveStateIds(ReportRequest request) {
        switch (request.getRegionFilter()) {
            case "CITY":
                return stateRepository.findByStateGroupCode(STATE_GROUP_CITY)
                        .stream().map(State::getId).collect(Collectors.toList());
            case "STATE":
                return List.of((request.getStateId()));
            default:
                return stateRepository.findAll()
                        .stream().map(State::getId).collect(Collectors.toList());
        }
    }
    //Расчет возраста в полных годах
    private int calculateAge(LocalDate birthDate, LocalDate diagnosisDate) {
        return (int) ChronoUnit.YEARS.between(birthDate, diagnosisDate);
    }

    //Загрузить все случаи за период по районам
    private List<DetectionCase> fetchCases(LocalDate from, LocalDate to, List<Long> stateIds) {
        return detectionCaseRepository.findAllForReports(from, to, stateIds);
    }

    //Проверка на мужской пол по code
    private boolean isMale(DetectionCase dc) {
        return GENDER_MALE.equals(dc.getPatient().getGender().getCode());
    }

    //Проверка из села по code
    private boolean isRural(DetectionCase dc) {
        return CITIZEN_TYPE_RURAL.equals(dc.getCitizenType().getCode());
    }

    //Проверка ДДУ по двум кодам
    private boolean isDdu(DetectionCase dc) {
        return DDU_CODES.contains(dc.getCitizenType().getCode());
    }

    //Относится ли район к Красноярску
    private boolean isCityDistrict(DetectionCase dc) {
        return STATE_GROUP_CITY.equals(dc.getState().getStateGroup().getCode());
    }

    //Фильтр случаев по ид группы диагнозов
    private List<DetectionCase> filterByDiagnosisGroup(List<DetectionCase> dc, Long groupId) {
        return dc.stream()
                .filter(c -> c.getDiagnosis().getDiagnosisGroup().getId().equals(groupId))
                .collect(Collectors.toList());
    }

    //Фильтр случаев по возрасту
    private List<DetectionCase> filterByAge(List<DetectionCase> dc, int minAge, int maxAge) {
        return dc.stream()
                .filter(c -> {
                    int age = calculateAge(c.getPatient().getBirthDate(), c.getDiagnosisDate());
                    return age >= minAge && age <= maxAge;
                })
                .collect(Collectors.toList());
    }

    //Имя группы диагнозов для заголовков excel
    private String getDiagnosisGroupName(Long groupId) {
        return diagnosisGroupRepository.findById(groupId)
                .map(DiagnosisGroup::getName).orElse("Неизвестно");
    }

    //Достаем из БД по id группы диагнозов code, чтобы понять какая это группа по коду
    private String getDiagnosisGroupCode(Long groupId) {
        return diagnosisGroupRepository.findById(groupId)
                .map(DiagnosisGroup::getCode).orElse("");
    }

    //Метод для контроллера, чтобы понять, сифилис ли это
    public boolean isSyphilisGroup(Long groupId) {
        return SYPHILIS_CODE.equals(getDiagnosisGroupCode(groupId));
    }

    //Отображаемое имя для заголовков столбцов
    private Map<String, String> buildGroupCodeToNameMap() {
        return diagnosisGroupRepository.findAll().stream()
                .collect(Collectors.toMap(DiagnosisGroup::getCode, DiagnosisGroup::getName));
    }

    //Строка ИТОГО для суммирования числовых столбцов
    private Map<String, Object> buildTotalRow(List<Map<String, Object>> rows, String labelKey, String[] sumKeys) {
        Map<String, Object> total = new LinkedHashMap<>();
        total.put(labelKey, "ИТОГО");
        for (String key : sumKeys) {
            int sum = rows.stream()
                    .map(r -> r.get(key))
                    .filter(Objects::nonNull)
                    .mapToInt(v -> ((Number) v).intValue())
                    .sum();
            total.put(key, sum > 0 ? sum : null);
        }
        return total;
    }

    //Один метод для 4 отчётов (Сифилис / Гонорея / Микроспория / Чесотка)
    //Строки = районы
    //Столбцы: Район | Все | До 17 | До 14 | До 1 | 1-2 | 3-6 | 3-6 ДДУ | Село | До 17 (село)

    public List<Map<String, Object>> generateStructureReport(ReportRequest request) {
        List<Long> stateIds = resolveStateIds(request);
        List<DetectionCase> allCases = fetchCases(request.getDateFrom(), request.getDateTo(), stateIds);
        List<DetectionCase> cases = filterByDiagnosisGroup(allCases, request.getDiagnosisGroupId());

        Map<String, List<DetectionCase>> byDistrict = cases.stream()
                .collect(Collectors.groupingBy(c -> c.getState().getName(), LinkedHashMap::new, Collectors.toList()));
        List<Map<String, Object>> rows = new ArrayList<>();

        for(var entry : byDistrict.entrySet()) {
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("district", entry.getKey());
            List<DetectionCase> dc = entry.getValue();
            row.put("total", dc.size());

            int under17 = 0, under14 = 0, under1 = 0;
            int age1to2 = 0, age3to6 = 0, age3to6ddu = 0;
            int rural = 0, under17rural = 0;

            for (DetectionCase c : dc) {
                int age = calculateAge(c.getPatient().getBirthDate(), c.getDiagnosisDate());
                boolean r = isRural(c);

                if (age <= 17) under17++;
                if (age <= 14) under14++;
                if (age <= 1) under1++;
                if (age >= 1 && age <= 2) age1to2++;
                if (age >= 3 && age <= 6) age3to6++;
                if (age >= 3 && age <= 6 && isDdu(c)) age3to6ddu++;
                if (r) rural++;
                if (age <= 17 && r) under17rural++;
            }

            row.put("under17", under17 > 0 ? under17 : null);
            row.put("under14", under14 > 0 ? under14 : null);
            row.put("under1",  under1 > 0 ? under1 : null);
            row.put("age1to2",  age1to2 > 0 ? age1to2 : null);
            row.put("age3to6",  age3to6 > 0 ? age3to6 : null);
            row.put("age3to6ddu",  age3to6ddu > 0 ? age3to6ddu : null);
            row.put("rural",  rural > 0 ? rural : null);
            row.put("under17rural",  under17rural > 0 ? under17rural : null);
            rows.add(row);
        }

        String[] sumKeys = {"total", "under17", "under14", "under1", "age1to2", "age3to6", "age3to6ddu", "rural", "under17rural"};
        rows.add(buildTotalRow(rows, "district", sumKeys));
        return rows;
    }

    //Показатели заболеваемости. Строки = районы, столбцы = заболевания
    public List<Map<String, Object>> generateIndicatorsReport(ReportRequest request) {
        List<Long> stateIds = resolveStateIds(request);
        List<DetectionCase> cases = fetchCases(request.getDateFrom(), request.getDateTo(), stateIds);
        Map<String, String> codeToName = buildGroupCodeToNameMap();

        Map<String, List<DetectionCase>> byDistrict = cases.stream()
                .collect(Collectors.groupingBy(c -> c.getState().getName(), LinkedHashMap::new, Collectors.toList()));

        List<Map<String, Object>> rows = new ArrayList<>();

        for (var entry : byDistrict.entrySet()) {
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("district", entry.getKey());

            for (String groupCode : INDICATORS_GROUP_CODES) {
                long count = entry.getValue().stream()
                        .filter(c -> c.getDiagnosis().getDiagnosisGroup().getCode().equals(groupCode)).count();
                String name = codeToName.getOrDefault(groupCode, groupCode);
                row.put(name, count > 0 ? count : null);
            }
            rows.add(row);
        }
        return rows;
    }

    //Заболеваемость ИППП на 100 тыс. населения
    public List<Map<String, Object>> generateIpppPer100kReport(ReportRequest request) {
        List<Long> stateIds = resolveStateIds(request);
        List<DetectionCase> cases = fetchCases(request.getDateFrom(), request.getDateTo(), stateIds);
        Map<String, String> codeToName = buildGroupCodeToNameMap();

        int year = request.getDateFrom().getYear();

        Map<Long, Integer> popByState = new HashMap<>();
        for (Population p : populationRepository.findByYear(year)) {
            popByState.put(p.getState().getId(), p.getCountAll());
        }

        Map<String, List<DetectionCase>> byDistrict = cases.stream()
                .collect(Collectors.groupingBy(c -> c.getState().getName(), LinkedHashMap::new, Collectors.toList()));
        List<Map<String, Object>> rows = new ArrayList<>();

        for (var entry : byDistrict.entrySet()) {
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("district", entry.getKey());
            Long stId = entry.getValue().get(0).getState().getId();
            Integer pop = popByState.get(stId);

            for (String groupCode : PER100K_GROUP_CODES) {
                String name = codeToName.getOrDefault(groupCode, groupCode);
                List<DetectionCase> dCases = entry.getValue().stream()
                        .filter(c -> c.getDiagnosis().getDiagnosisGroup().getCode().equals(groupCode)).toList();
                int count = dCases.size();

                row.put(name + " Всего", count > 0 ? count : null);
                row.put(name + " на 100т.", count > 0 && pop != null && pop > 0 ? Math.round((double) count * 100000 / pop * 10.0) / 10.0 : null);

                long teens = dCases.stream().filter(c -> {
                    int age = calculateAge(c.getPatient().getBirthDate(), c.getDiagnosisDate());
                    return age >= 15 && age <= 17;
                }).count();
                row.put(name + " 15-17 лет", teens > 0 ? (int) teens : null);
            }
            rows.add(row);
        }

        Map<String, Object> total = new LinkedHashMap<>();
        total.put("district", "ИТОГО");
        for (String groupCode : PER100K_GROUP_CODES) {
            String name = codeToName.getOrDefault(groupCode, groupCode);
            for (String s : new String[]{" Всего", " на 100т.", " 15-17 лет"}) {
                String key = name + s;
                if (s.equals(" на 100т.")) {
                    total.put(key, null);
                } else {
                    int sum = rows.stream().map(r -> r.get(key))
                            .filter(Objects::nonNull)
                            .mapToInt(v -> ((Number) v).intValue()).sum();
                    total.put(key, sum > 0 ? sum : null);
                }
            }
        }
        rows.add(total);
        return rows;
    }

}
