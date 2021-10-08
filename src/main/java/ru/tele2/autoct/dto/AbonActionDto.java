package ru.tele2.autoct.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.tele2.autoct.dto.dictionaries.AbonDictionaryDto;

@Setter
@Getter
@NoArgsConstructor
public class AbonActionDto {

    private Long id;

    private AbonDictionaryDto abonDict;

    private BTEActionDto bteAction;
}
