package ru.tele2.autoct.mappers.additionalParams;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.tele2.autoct.dto.AdditionalParamDto;
import ru.tele2.autoct.dto.additionalParams.ClntIdDto;


@Mapper
public interface ClntIdMapper {

    @Mappings({
            @Mapping(target="clntId", source="param.paramId"),
            @Mapping(target="unit", source="param.paramValue")
    })
    ClntIdDto convert(AdditionalParamDto param);
}
