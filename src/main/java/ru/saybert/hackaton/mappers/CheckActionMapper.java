package ru.saybert.hackaton.mappers;

import org.mapstruct.Mapper;
import ru.saybert.hackaton.mappers.dictionaries.CheckDictionaryMapper;
import ru.saybert.hackaton.dto.CheckActionDto;
import ru.saybert.hackaton.jpa.entity.CheckActionEntity;

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
