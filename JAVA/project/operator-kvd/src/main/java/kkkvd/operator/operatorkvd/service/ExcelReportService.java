package kkkvd.operator.operatorkvd.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ExcelReportService {
    private static final Map<String, String> HEADER_LABELS = Map.ofEntries(
            //структура заболеваемости (отчеты 1 - 4)
            Map.entry("district", "Район"),
            Map.entry("total", "Всего"),
            Map.entry("under17", "До 17 лет"),
            Map.entry("under14", "До 14 лет"),
            Map.entry("under1", "До 1 года"),
            Map.entry("age1to2", "1-2 года"),
            Map.entry("age3to6", "3-6 лет"),
            Map.entry("age3to6ddu", "3-6 ДДУ"),
            Map.entry("rural", "Село"),
            Map.entry("under17rural", "До 17 (село)"),
            //сведения ИППП (отчет 7)
            Map.entry("diagnosis", "Диагноз"),
            Map.entry("gender", "Пол"),
            Map.entry("count", "Кол-во"),
            Map.entry("age0_1", "0-1 год"),
            Map.entry("age2_14", "2-14 лет"),
            Map.entry("age15_17", "15-17 лет"),
            Map.entry("age18_29", "18-29 лет"),
            Map.entry("age30_39", "30-39 лет"),
            Map.entry("age40plus", "40 и старше"),
            Map.entry("rural0_1", "Село 0-1"),
            Map.entry("rural2_14", "Село 2-14"),
            Map.entry("rural15_17", "Село 15-17"),
            //список по врачам (отчёт 8)
            Map.entry("patient", "Пациент"),
            Map.entry("birthDate", "Дата рождения"),
            Map.entry("doctor", "Врач"),
            //подробный отчет (отчёт 9)
            Map.entry("name", "Наименование"),
            Map.entry("male", "Муж"),
            Map.entry("female", "Жен"),
            Map.entry("percent", "%"),
            Map.entry("per100k", "На 100 тыс.")
    );

}
