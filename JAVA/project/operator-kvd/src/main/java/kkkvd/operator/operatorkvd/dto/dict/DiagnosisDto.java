package kkkvd.operator.operatorkvd.dto.dict;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DiagnosisDto {
    private Long id;
    private String name;
    private Long diagnosisGroupId;
    private String diagnosisGroupName;
}
