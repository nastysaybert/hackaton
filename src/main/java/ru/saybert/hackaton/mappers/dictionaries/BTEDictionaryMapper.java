package ru.saybert.hackaton.mappers.dictionaries;

import org.mapstruct.Mapper;
import ru.saybert.hackaton.dto.dictionaries.BTEDictionaryDto;
import ru.saybert.hackaton.jpa.entity.dictionaries.BTEDictionaryEntity;

@Mapper(componentModel = "spring")
public interface BTEDictionaryMapper {

    BTEDictionaryDto convert(BTEDictionaryEntity bteDict);

    BTEDictionaryEntity convert(BTEDictionaryDto bteDict);
}
