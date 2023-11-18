package ru.saybert.hackaton.mappers.additionalParams;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.saybert.hackaton.dto.AdditionalParamDto;
import ru.saybert.hackaton.dto.additionalParams.ZoneDto;
import ru.saybert.hackaton.jpa.entity.additionalParams.ZoneEntity;

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
