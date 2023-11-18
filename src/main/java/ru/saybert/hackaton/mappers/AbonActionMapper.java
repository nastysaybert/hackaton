package ru.saybert.hackaton.mappers;

import org.mapstruct.Mapper;
import ru.saybert.hackaton.jpa.entity.AbonActionEntity;
import ru.saybert.hackaton.mappers.dictionaries.AbonDictionaryMapper;
import ru.saybert.hackaton.dto.AbonActionDto;

@Mapper(
        componentModel = "spring",
        uses = {
                AbonDictionaryMapper.class,
                BTEActionMapper.class
        })
public interface AbonActionMapper {

    AbonActionDto convert(AbonActionEntity abonAction);

    AbonActionEntity convert(AbonActionDto abonAction);
}
