package ru.tele2.autoct.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.tele2.autoct.dto.dictionaries.AbonDictionaryDto;
import ru.tele2.autoct.jpa.entity.BTEActionEntity;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class AbonActionDto {

    private Long id;

    private AbonDictionaryDto abonDict;

//    private BTEActionDto bteAction;
    private List<BTEActionDto> bteActions;

    private String comment;
}
