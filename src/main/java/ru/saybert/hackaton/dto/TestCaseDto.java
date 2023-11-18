package ru.saybert.hackaton.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.saybert.hackaton.dto.dictionaries.ProjectDto;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class TestCaseDto {

    private Long id;

    private String name;

    private InitialDataDto initialData;

    private List<TestCaseStepDto> testCaseStepList;

    private boolean template;

    private LocalDateTime delDate;

    private String author;

    private String redactor;

    private ProjectDto project;

}
