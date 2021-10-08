package ru.tele2.autoct.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.tele2.autoct.enums.ParamType;

@Setter
@Getter
@NoArgsConstructor
public class BTEDictionaryDto {

    private Long id;

    private String name;

    private ParamType paramType;
}
