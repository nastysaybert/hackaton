package ru.tele2.autoct.mappers.dictionaries;

import org.mapstruct.Mapper;
import ru.tele2.autoct.dto.dictionaries.BTEDictionaryDto;
import ru.tele2.autoct.jpa.entity.dictionaries.BTEDictionaryEntity;

@Mapper(componentModel = "spring")
public interface BTEDictionaryMapper {

    BTEDictionaryDto convert(BTEDictionaryEntity bteDict);

    BTEDictionaryEntity convert(BTEDictionaryDto bteDict);
}
