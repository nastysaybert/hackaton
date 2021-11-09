package ru.tele2.autoct.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.tele2.autoct.dto.BTEActionDto;
import ru.tele2.autoct.dto.dictionaries.CheckDictionaryDto;
import ru.tele2.autoct.jpa.entity.BTEActionEntity;

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
