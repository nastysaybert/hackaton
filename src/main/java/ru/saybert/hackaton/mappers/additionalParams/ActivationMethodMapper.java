package ru.saybert.hackaton.mappers.additionalParams;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.saybert.hackaton.dto.AdditionalParamDto;
import ru.saybert.hackaton.dto.additionalParams.ActivationMethodDto;
import ru.saybert.hackaton.jpa.entity.additionalParams.ActivationMethodEntity;

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
