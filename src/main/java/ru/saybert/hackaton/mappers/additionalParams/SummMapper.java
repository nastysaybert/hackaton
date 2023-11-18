package ru.saybert.hackaton.mappers.additionalParams;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.saybert.hackaton.dto.AdditionalParamDto;
import ru.saybert.hackaton.dto.additionalParams.SummDto;

@Mapper
public interface SummMapper {

    @Mappings({
            @Mapping(target="amount", source="param.paramId"),
            @Mapping(target="unit", source="param.paramValue")
    })
    SummDto convert(AdditionalParamDto param);
}
