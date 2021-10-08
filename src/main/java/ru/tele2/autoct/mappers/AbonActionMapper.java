package ru.tele2.autoct.mappers;

import org.mapstruct.Mapper;
import ru.tele2.autoct.dto.AbonActionDto;
import ru.tele2.autoct.jpa.entity.AbonActionEntity;
import ru.tele2.autoct.mappers.dictionaries.AbonDictionaryMapper;

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
