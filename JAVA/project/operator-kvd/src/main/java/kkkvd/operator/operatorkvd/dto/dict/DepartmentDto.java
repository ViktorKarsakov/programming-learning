package kkkvd.operator.operatorkvd.dto.dict;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DepartmentDto {
    private Long id;
    private String name;
    private Long branchId;
    private String branchName;
}
