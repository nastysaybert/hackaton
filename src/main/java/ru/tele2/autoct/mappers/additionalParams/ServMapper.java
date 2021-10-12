package ru.tele2.autoct.mappers.additionalParams;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.tele2.autoct.dto.AdditionalParamDto;
import ru.tele2.autoct.dto.additionalParams.ServDto;
import ru.tele2.autoct.jpa.entity.additionalParams.ServEntity;

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
