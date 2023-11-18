package ru.saybert.hackaton.dto;

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

    private AbonActionDto abonAction;

    private List<CheckActionDto> checkActions;
}
