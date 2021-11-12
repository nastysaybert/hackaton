package ru.tele2.autoct.mappers.additionalParams;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.tele2.autoct.dto.AdditionalParamDto;
import ru.tele2.autoct.dto.additionalParams.ActivationMethodDto;
import ru.tele2.autoct.jpa.entity.additionalParams.ActivationMethodEntity;

@Mapper(componentModel = "spring")
public interface ActivationMethodMapper {

    ActivationMethodDto convert(ActivationMethodEntity activationMethod);

    ActivationMethodEntity convert(ActivationMethodDto activationMethod);

    @Mappings({
            @Mapping(target="method", source="param.paramId"),
            @Mapping(target="description", source="param.paramValue")
    })
    ActivationMethodDto convert(AdditionalParamDto param);
}
