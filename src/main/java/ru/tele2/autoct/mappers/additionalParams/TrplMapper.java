package ru.tele2.autoct.mappers.additionalParams;

import org.mapstruct.Mapper;
import ru.tele2.autoct.dto.additionalParams.TrplDto;
import ru.tele2.autoct.jpa.entity.additionalParams.TrplEntity;

@Mapper(componentModel = "spring")
public interface TrplMapper {

    TrplDto convert(TrplEntity trpl);

    TrplEntity convert(TrplDto trpl);
}
