package ru.saybert.hackaton.services;

import ru.saybert.hackaton.dto.TestCaseDto;
import ru.saybert.hackaton.dto.dictionaries.ProjectDto;

import java.util.List;

public interface TestCaseService {

    TestCaseDto getTestCaseDtoFromForm(TestCaseForm testCaseForm);

    boolean save(TestCaseDto testCaseDto);

    void delete(TestCaseDto testCaseDto);

    TestCaseDto getById(Long id);

    List<TestCaseDto> getAll();

    List<TestCaseDto> getAllTemplates();

    List<TestCaseDto> getAllTestCases();

    void setDelDate(TestCaseDto testCaseDto);

    List<TestCaseDto> getAllDeleted();

    List<TestCaseDto> getAllByProject(ProjectDto project);
}
