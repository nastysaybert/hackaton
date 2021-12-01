package ru.tele2.autoct.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.tele2.autoct.dto.dictionaries.ProjectDto;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
