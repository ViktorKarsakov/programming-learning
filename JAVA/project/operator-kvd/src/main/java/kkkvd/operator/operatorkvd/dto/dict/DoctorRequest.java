package kkkvd.operator.operatorkvd.dto.dict;

import lombok.Data;

@Data
public class DoctorRequest {
    private String lastName;
    private String firstName;
    private String middleName;
    private Long departmentId;
}
