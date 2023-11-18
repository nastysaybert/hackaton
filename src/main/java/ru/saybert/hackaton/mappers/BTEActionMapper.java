package ru.saybert.hackaton.mappers;

import org.mapstruct.Mapper;
import ru.saybert.hackaton.jpa.entity.BTEActionEntity;
import ru.saybert.hackaton.dto.BTEActionDto;

@Mapper(componentModel = "spring")
public interface BTEActionMapper {

    BTEActionDto convert(BTEActionEntity bteAction);

    BTEActionEntity convert(BTEActionDto bteAction);

}
