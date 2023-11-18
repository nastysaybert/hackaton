package ru.saybert.hackaton.mappers.additionalParams;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.saybert.hackaton.dto.AdditionalParamDto;
import ru.saybert.hackaton.dto.additionalParams.ClntIdDto;


@Mapper
public interface ClntIdMapper {

    @Mappings({
            @Mapping(target="clntId", source="param.paramId"),
            @Mapping(target="unit", source="param.paramValue")
    })
    ClntIdDto convert(AdditionalParamDto param);
}
