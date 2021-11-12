package ru.tele2.autoct.mappers.additionalParams;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.tele2.autoct.dto.AdditionalParamDto;
import ru.tele2.autoct.dto.additionalParams.ZoneDto;
import ru.tele2.autoct.jpa.entity.additionalParams.ZoneEntity;

@Mapper (componentModel = "spring")
public interface ZoneMapper {

    ZoneDto convert(ZoneEntity zone);

    ZoneEntity convert(ZoneDto zone);

    @Mappings({
            @Mapping(target="zoneId", source="param.paramId"),
            @Mapping(target="zoneName", source="param.paramValue")
    })
    ZoneDto convert(AdditionalParamDto param);
}
