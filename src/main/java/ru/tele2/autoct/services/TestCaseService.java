package ru.tele2.autoct.services;

import ru.tele2.autoct.dto.TestCaseDto;
import ru.tele2.autoct.views.components.TestCaseForm;

public interface TestCaseService {

    TestCaseDto getTestCaseDtoFromForm(TestCaseForm testCaseForm);

    boolean save(TestCaseDto testCaseDto);
}
