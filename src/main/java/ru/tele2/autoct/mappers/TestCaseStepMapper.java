package ru.tele2.autoct.mappers;

import org.mapstruct.Mapper;
import ru.tele2.autoct.dto.TestCaseStepDto;
import ru.tele2.autoct.jpa.entity.TestCaseStepEntity;

@Mapper(
        componentModel = "spring",
        uses = {
                AbonActionMapper.class,
                CheckActionMapper.class
        })
public interface TestCaseStepMapper {

    TestCaseStepDto convert(TestCaseStepEntity testCaseStep);

    TestCaseStepEntity convert(TestCaseStepDto testCaseStep);
}
