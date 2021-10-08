package ru.tele2.autoct.dto.dictionaries;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.tele2.autoct.jpa.entity.dictionaries.BTEDictionaryEntity;
import ru.tele2.autoct.jpa.entity.dictionaries.CheckDictionaryEntity;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class AbonDictionaryDto {

    private Long abonDictId;

    private String abonDictName;

    private BTEDictionaryEntity bteDictionary;

    private Set<CheckDictionaryEntity> checkDicts;

}
