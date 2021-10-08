package ru.tele2.autoct.mappers;

import org.mapstruct.Mapper;
import ru.tele2.autoct.dto.CheckActionDto;
import ru.tele2.autoct.jpa.entity.CheckActionEntity;
import ru.tele2.autoct.mappers.dictionaries.CheckDictionaryMapper;

@Mapper(
        componentModel = "spring",
        uses = {
                CheckDictionaryMapper.class,
                BTEActionMapper.class
        })
public interface CheckActionMapper {

    CheckActionDto convert(CheckActionEntity checkAction);

    CheckActionEntity convert(CheckActionDto checkAction);
}
