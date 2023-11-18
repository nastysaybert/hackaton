package ru.saybert.hackaton.mappers.additionalParams;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.saybert.hackaton.dto.AdditionalParamDto;
import ru.saybert.hackaton.dto.additionalParams.AuthLevelDto;
import ru.saybert.hackaton.jpa.entity.additionalParams.AuthLevelEntity;

@Mapper(componentModel = "spring")
public interface AuthLevelMapper {

    AuthLevelDto convert(AuthLevelEntity branch);

    AuthLevelEntity convert(AuthLevelDto branch);

    @Mappings({
            @Mapping(target="authLevelId", source="param.paramId"),
            @Mapping(target="authLevelName", source="param.paramValue")
    })
    AuthLevelDto convert(AdditionalParamDto param);
}
