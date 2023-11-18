package ru.saybert.hackaton.mappers;

import org.mapstruct.Mapper;
import ru.saybert.hackaton.jpa.entity.TestCaseEntity;
import ru.saybert.hackaton.mappers.dictionaries.ProjectMapper;
import ru.saybert.hackaton.dto.TestCaseDto;

@Mapper(
        componentModel = "spring",
        uses = {
                TestCaseStepMapper.class,
                InitialDataMapper.class,
                ProjectMapper.class
        })
public interface TestCaseMapper {

    TestCaseDto convert(TestCaseEntity testCase);

    TestCaseEntity convert(TestCaseDto testCase);
}
