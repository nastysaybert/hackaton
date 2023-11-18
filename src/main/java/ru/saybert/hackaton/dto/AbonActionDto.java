package ru.saybert.hackaton.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.saybert.hackaton.dto.dictionaries.AbonDictionaryDto;

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
