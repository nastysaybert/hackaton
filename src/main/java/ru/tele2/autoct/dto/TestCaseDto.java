package ru.tele2.autoct.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class TestCaseDto {

    private Long id;

    private List<InitialDataDto> initialDataList;

    private List<TestCaseStepDto> testCaseStepList;

    private boolean isTemplate;

}