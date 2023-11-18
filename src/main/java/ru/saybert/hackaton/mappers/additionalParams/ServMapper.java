package ru.saybert.hackaton.mappers.additionalParams;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.saybert.hackaton.dto.AdditionalParamDto;
import ru.saybert.hackaton.dto.additionalParams.ServDto;
import ru.saybert.hackaton.jpa.entity.additionalParams.ServEntity;

@Mapper(componentModel = "spring")
public interface ServMapper {

    ServDto convert(ServEntity serv);

    ServEntity convert(ServDto serv);

    @Mappings({
            @Mapping(target="servId", source="param.paramId"),
            @Mapping(target="servName", source="param.paramValue")
    })
    ServDto convert(AdditionalParamDto param);
}
