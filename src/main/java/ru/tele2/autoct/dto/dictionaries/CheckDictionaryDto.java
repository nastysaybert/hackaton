package ru.tele2.autoct.dto.dictionaries;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.tele2.autoct.jpa.entity.dictionaries.AbonDictionaryEntity;
import ru.tele2.autoct.jpa.entity.dictionaries.BTEDictionaryEntity;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class CheckDictionaryDto {

    private Long checkDictId;

    private String checkDictName;

    private BTEDictionaryEntity bteAction;

    private Set<AbonDictionaryEntity> abonDicts;

}
