package ru.saybert.hackaton.dto.dictionaries;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BTEDictionaryDto {

    private Long bteDictId;

    private String bteDictName;

    private String paramType;
}
