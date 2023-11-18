package ru.saybert.hackaton.mappers.additionalParams;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.saybert.hackaton.dto.AdditionalParamDto;
import ru.saybert.hackaton.dto.additionalParams.TechnologyTypeDto;
import ru.saybert.hackaton.jpa.entity.additionalParams.TechnologyTypeEntity;

@Mapper(componentModel = "spring")
public interface TechnologyTypeMapper {

    TechnologyTypeDto convert(TechnologyTypeEntity branch);

    TechnologyTypeEntity convert(TechnologyTypeDto branch);

    @Mappings({
            @Mapping(target="technologyTypeId", source="param.paramId"),
            @Mapping(target="technologyTypeName", source="param.paramValue")
    })
    TechnologyTypeDto convert(AdditionalParamDto param);
}
