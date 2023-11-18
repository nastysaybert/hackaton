package ru.saybert.hackaton.mappers.dictionaries;

import org.mapstruct.Mapper;
import ru.saybert.hackaton.dto.dictionaries.CheckDictionaryDto;
import ru.saybert.hackaton.jpa.entity.dictionaries.CheckDictionaryEntity;

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
