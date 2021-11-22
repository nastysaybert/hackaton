package ru.tele2.autoct.mappers.additionalParams;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.tele2.autoct.dto.AdditionalParamDto;
import ru.tele2.autoct.dto.additionalParams.ClientTypeDto;
import ru.tele2.autoct.jpa.entity.additionalParams.ClientTypeEntity;

@Mapper(componentModel = "spring")
public interface ClientTypeMapper {

    ClientTypeDto convert(ClientTypeEntity branch);

    ClientTypeEntity convert(ClientTypeDto branch);

    @Mappings({
            @Mapping(target="clientTypeId", source="param.paramId"),
            @Mapping(target="clientTypeName", source="param.paramValue")
    })
    ClientTypeDto convert(AdditionalParamDto param);
}
