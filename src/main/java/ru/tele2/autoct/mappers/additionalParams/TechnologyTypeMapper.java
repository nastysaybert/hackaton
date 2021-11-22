package ru.tele2.autoct.mappers.additionalParams;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.tele2.autoct.dto.AdditionalParamDto;
import ru.tele2.autoct.dto.additionalParams.TechnologyTypeDto;
import ru.tele2.autoct.jpa.entity.additionalParams.TechnologyTypeEntity;

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
