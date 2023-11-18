package ru.saybert.hackaton.dto.dictionaries;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class CheckDictionaryDto {

    private Long checkDictId;

    private String checkDictName;

    private BTEDictionaryDto bteDictionary;

    private List<AbonDictionaryDto> abonDicts;

}
