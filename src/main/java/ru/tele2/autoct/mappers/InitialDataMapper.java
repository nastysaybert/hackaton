package ru.tele2.autoct.mappers;

import org.mapstruct.Mapper;
import ru.tele2.autoct.dto.InitialDataDto;
import ru.tele2.autoct.jpa.entity.InitialDataEntity;

@Mapper(componentModel = "spring")
public interface InitialDataMapper {

    InitialDataDto convert(InitialDataEntity initData);

    InitialDataEntity convert(InitialDataDto initData);
}
