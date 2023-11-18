package ru.saybert.hackaton.mappers.additionalParams;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.saybert.hackaton.dto.AdditionalParamDto;
import ru.saybert.hackaton.dto.additionalParams.TrplDto;
import ru.saybert.hackaton.jpa.entity.additionalParams.TrplEntity;

@Mapper(componentModel = "spring")
public interface TrplMapper {

    TrplDto convert(TrplEntity trpl);

    TrplEntity convert(TrplDto trpl);

    @Mappings({
            @Mapping(target="trplId", source="param.paramId"),
            @Mapping(target="trplName", source="param.paramValue")
    })
    TrplDto convert(AdditionalParamDto param);


}
