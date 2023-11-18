package ru.saybert.hackaton.mappers.additionalParams;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.saybert.hackaton.dto.AdditionalParamDto;
import ru.saybert.hackaton.dto.additionalParams.ClientTypeDto;
import ru.saybert.hackaton.jpa.entity.additionalParams.ClientTypeEntity;

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
