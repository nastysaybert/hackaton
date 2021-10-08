package ru.tele2.autoct.mappers.additionalParams;

import org.mapstruct.Mapper;
import ru.tele2.autoct.dto.additionalParams.ServDto;
import ru.tele2.autoct.jpa.entity.additionalParams.ServEntity;

@Mapper(componentModel = "spring")
public interface ServMapper {

    ServDto convert(ServEntity serv);

    ServEntity convert(ServDto serv);
}
