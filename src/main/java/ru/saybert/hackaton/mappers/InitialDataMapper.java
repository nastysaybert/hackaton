package ru.saybert.hackaton.mappers;

import org.mapstruct.Mapper;
import ru.saybert.hackaton.jpa.entity.InitialDataEntity;
import ru.saybert.hackaton.dto.InitialDataDto;

@Mapper(componentModel = "spring")
public interface InitialDataMapper {

    InitialDataDto convert(InitialDataEntity initData);

    InitialDataEntity convert(InitialDataDto initData);
}
