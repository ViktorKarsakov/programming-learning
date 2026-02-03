package kkkvd.operator.operatorkvd.dto.dict;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StateDto {
    private Long id;
    private String name;
    private Long stateGroupId;
    private String stateGroupName;
}
