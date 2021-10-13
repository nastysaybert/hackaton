package ru.tele2.autoct.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.tele2.autoct.dto.AdditionalParamDto;
import ru.tele2.autoct.dto.additionalParams.USSDRequestDto;

@Mapper
public interface USSDRequestMapper {
    @Mappings({
            @Mapping(target="ussd", source="param.paramId"),
            @Mapping(target="description", source="param.paramValue")
    })
    USSDRequestDto convert(AdditionalParamDto param);
}
