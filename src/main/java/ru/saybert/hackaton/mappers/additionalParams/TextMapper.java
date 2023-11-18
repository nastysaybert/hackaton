package ru.saybert.hackaton.mappers.additionalParams;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.saybert.hackaton.dto.AdditionalParamDto;
import ru.saybert.hackaton.dto.additionalParams.TextDto;

@Mapper
public interface TextMapper {

    @Mappings({
            @Mapping(target="count", source="param.paramId"),
            @Mapping(target="unit", source="param.paramValue")
    })
    TextDto convert(AdditionalParamDto param);
}
