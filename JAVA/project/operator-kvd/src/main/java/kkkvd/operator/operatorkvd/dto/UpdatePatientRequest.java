package kkkvd.operator.operatorkvd.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdatePatientRequest {
    //Редактировать поля пациента (личные данные)

    private String lastName;
    private String firstName;
    private String middleName;
    private LocalDate birthDate;
    private Long genderId;
    private String address;
}
