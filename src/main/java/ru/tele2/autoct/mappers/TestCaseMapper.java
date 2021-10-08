package ru.tele2.autoct.mappers;

import org.mapstruct.Mapper;
import ru.tele2.autoct.dto.TestCaseDto;
import ru.tele2.autoct.jpa.entity.TestCaseEntity;

@Mapper(
        componentModel = "spring",
        uses = {
                TestCaseStepMapper.class,
                InitialDataMapper.class
        })
public interface TestCaseMapper {

    TestCaseDto convert(TestCaseEntity testCase);

    TestCaseEntity convert(TestCaseDto testCase);
}
