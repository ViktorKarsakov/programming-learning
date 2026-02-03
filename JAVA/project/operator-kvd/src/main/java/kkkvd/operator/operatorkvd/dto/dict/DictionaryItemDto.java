package kkkvd.operator.operatorkvd.dto.dict;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DictionaryItemDto {
    private Long id;
    private String name;
}
