package kkkvd.operator.operatorkvd.dto.dict;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DoctorDto {
    private Long id;
    private String lastName;
    private String firstName;
    private String middleName;
    private Long departmentId;
    private String departmentName;
}
