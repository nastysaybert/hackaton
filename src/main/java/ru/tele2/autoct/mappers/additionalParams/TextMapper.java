package ru.tele2.autoct.mappers.additionalParams;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.tele2.autoct.dto.AdditionalParamDto;
import ru.tele2.autoct.dto.additionalParams.TextDto;

@Mapper
public interface TextMapper {

    @Mappings({
            @Mapping(target="count", source="param.paramId"),
            @Mapping(target="unit", source="param.paramValue")
    })
    TextDto convert(AdditionalParamDto param);
}
