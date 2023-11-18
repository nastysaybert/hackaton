package ru.saybert.hackaton.mappers;

import org.mapstruct.Mapper;
import ru.saybert.hackaton.jpa.entity.TestCaseStepEntity;
import ru.saybert.hackaton.dto.TestCaseStepDto;

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
