package ru.saybert.hackaton.mappers.dictionaries;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.saybert.hackaton.dto.dictionaries.AbonDictionaryDto;
import ru.saybert.hackaton.jpa.entity.dictionaries.AbonDictionaryEntity;


@Mapper(
        componentModel = "spring",
        uses = {
                BTEDictionaryMapper.class,
                CheckDictionaryMapper.class
        }
)
public interface AbonDictionaryMapper {
    @Mapping(target = "checkDicts", ignore = true)
    AbonDictionaryDto convert (AbonDictionaryEntity abonDict);

    AbonDictionaryEntity convert (AbonDictionaryDto abonDict);
}
