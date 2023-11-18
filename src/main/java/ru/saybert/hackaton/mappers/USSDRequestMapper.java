package ru.saybert.hackaton.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.saybert.hackaton.dto.AdditionalParamDto;
import ru.saybert.hackaton.dto.additionalParams.USSDRequestDto;

@Mapper
public interface USSDRequestMapper {
    @Mappings({
            @Mapping(target="ussd", source="param.paramId"),
            @Mapping(target="description", source="param.paramValue")
    })
    USSDRequestDto convert(AdditionalParamDto param);
}
