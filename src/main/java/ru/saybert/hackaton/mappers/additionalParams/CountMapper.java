package ru.saybert.hackaton.mappers.additionalParams;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.saybert.hackaton.dto.AdditionalParamDto;
import ru.saybert.hackaton.dto.additionalParams.DurationDto;

@Mapper
public interface CountMapper {

    @Mappings({
            @Mapping(target="duration", source="param.paramId"),
            @Mapping(target="unit", source="param.paramValue")
    })
    DurationDto convert(AdditionalParamDto param);
}
