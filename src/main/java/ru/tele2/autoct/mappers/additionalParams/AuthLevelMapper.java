package ru.tele2.autoct.mappers.additionalParams;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.tele2.autoct.dto.AdditionalParamDto;
import ru.tele2.autoct.dto.additionalParams.AuthLevelDto;
import ru.tele2.autoct.jpa.entity.additionalParams.AuthLevelEntity;

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
