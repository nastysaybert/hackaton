package ru.tele2.autoct.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class TestCaseStepDto {

    private Long id;

    private Long stepNumber;

    private AbonDictionaryDto abonAction;

    private List<CheckDictionaryDto> checkActions;
}
