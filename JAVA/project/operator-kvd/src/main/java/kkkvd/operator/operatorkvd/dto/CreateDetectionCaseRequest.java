package kkkvd.operator.operatorkvd.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class CreateDetectionCaseRequest {

    private String lastName;
    private String firstName;
    private String middleName;
    private LocalDate birthDate;
    private Long genderId;
    private String address;

    private Long citizenCategoryId;
    private Long citizenTypeId;
    private Long stateId;
    private Long socialGroupId;

    private Long diagnosisId;
    private LocalDate diagnosisDate;
    private Long doctorId;
    private Long placeId;
    private Long profileId;
    private Long inspectionId;
    private Long transferId;
    private Boolean isContact;

    private Set<Long> labTestIds;
}
