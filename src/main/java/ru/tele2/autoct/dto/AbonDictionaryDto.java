package ru.tele2.autoct.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class AbonDictionaryDto {

    private Long id;

    private String name;

    private BTEDictionaryDto bteAction;

    private Set<CheckDictionaryDto> checkActions;
}
