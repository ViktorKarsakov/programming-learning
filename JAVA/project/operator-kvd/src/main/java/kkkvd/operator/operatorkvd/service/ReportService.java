package kkkvd.operator.operatorkvd.service;

import kkkvd.operator.operatorkvd.dto.ReportRequest;
import kkkvd.operator.operatorkvd.entities.State;
import kkkvd.operator.operatorkvd.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
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

}
