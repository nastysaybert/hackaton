package ru.tele2.autoct.mappers.additionalParams;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.tele2.autoct.dto.AdditionalParamDto;
import ru.tele2.autoct.dto.additionalParams.DurationDto;

@Mapper
public interface DurationMapper {

    @Mappings({
            @Mapping(target="duration", source="param.paramId"),
            @Mapping(target="unit", source="param.paramValue")
    })
    DurationDto convert(AdditionalParamDto param);
}
