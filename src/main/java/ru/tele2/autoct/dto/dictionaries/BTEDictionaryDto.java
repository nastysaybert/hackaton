package ru.tele2.autoct.dto.dictionaries;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.tele2.autoct.enums.ParamType;

@Setter
@Getter
@NoArgsConstructor
public class BTEDictionaryDto {

    private Long bteDictId;

    private String bteDictName;

    private String paramType;
}
