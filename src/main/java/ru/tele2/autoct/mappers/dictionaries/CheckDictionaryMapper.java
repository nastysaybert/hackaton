package ru.tele2.autoct.mappers.dictionaries;

import org.mapstruct.Mapper;
import ru.tele2.autoct.dto.dictionaries.CheckDictionaryDto;
import ru.tele2.autoct.jpa.entity.dictionaries.CheckDictionaryEntity;

@Mapper(
        componentModel = "spring",
        uses = {
                BTEDictionaryMapper.class,
                AbonDictionaryMapper.class
        }
)
public interface CheckDictionaryMapper {
    CheckDictionaryDto convert (CheckDictionaryEntity checkDict);
    CheckDictionaryEntity convert (CheckDictionaryDto checkDict);
}
