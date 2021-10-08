package ru.tele2.autoct.mappers;

import org.mapstruct.Mapper;
import ru.tele2.autoct.dto.BTEActionDto;
import ru.tele2.autoct.jpa.entity.BTEActionEntity;

@Mapper(componentModel = "spring")
public interface BTEActionMapper {

    BTEActionDto convert(BTEActionEntity bteAction);

    BTEActionEntity convert(BTEActionDto bteAction);

}
