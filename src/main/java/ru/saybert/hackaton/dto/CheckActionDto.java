package ru.saybert.hackaton.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.saybert.hackaton.dto.dictionaries.CheckDictionaryDto;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class CheckActionDto {

    private Long id;

    private CheckDictionaryDto checkDict;

//    private BTEActionDto bteAction;
    private List<BTEActionDto> bteActions;

    private String comment;
}
