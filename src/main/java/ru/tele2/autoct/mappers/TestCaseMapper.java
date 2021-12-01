package ru.tele2.autoct.mappers;

import org.mapstruct.Mapper;
import ru.tele2.autoct.dto.TestCaseDto;
import ru.tele2.autoct.jpa.entity.TestCaseEntity;
import ru.tele2.autoct.mappers.dictionaries.ProjectMapper;

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
