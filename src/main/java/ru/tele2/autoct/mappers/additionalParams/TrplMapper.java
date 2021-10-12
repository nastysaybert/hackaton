package ru.tele2.autoct.mappers.additionalParams;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.tele2.autoct.dto.AdditionalParamDto;
import ru.tele2.autoct.dto.additionalParams.TrplDto;
import ru.tele2.autoct.jpa.entity.additionalParams.TrplEntity;

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
