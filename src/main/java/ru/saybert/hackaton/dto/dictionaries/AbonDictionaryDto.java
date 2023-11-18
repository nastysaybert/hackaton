package ru.saybert.hackaton.dto.dictionaries;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class AbonDictionaryDto {

    private Long abonDictId;

    private String abonDictName;

    private BTEDictionaryDto bteDictionary;

    private List<CheckDictionaryDto> checkDicts;

}
