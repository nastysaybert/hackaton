package ru.tele2.autoct.mappers.dictionaries;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.tele2.autoct.dto.dictionaries.AbonDictionaryDto;
import ru.tele2.autoct.jpa.entity.dictionaries.AbonDictionaryEntity;


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
