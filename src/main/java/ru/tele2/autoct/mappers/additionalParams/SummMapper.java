package ru.tele2.autoct.mappers.additionalParams;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.tele2.autoct.dto.AdditionalParamDto;
import ru.tele2.autoct.dto.additionalParams.SummDto;

@Mapper
public interface SummMapper {

    @Mappings({
            @Mapping(target="amount", source="param.paramId"),
            @Mapping(target="unit", source="param.paramValue")
    })
    SummDto convert(AdditionalParamDto param);
}
