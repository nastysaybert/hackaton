package ru.tele2.autoct.dto.dictionaries;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class CheckDictionaryDto {

    private Long checkDictId;

    private String checkDictName;

    private BTEDictionaryDto bteDictionary;

    private List<AbonDictionaryDto> abonDicts;

}
