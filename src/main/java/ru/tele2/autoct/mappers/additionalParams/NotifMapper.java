package ru.tele2.autoct.mappers.additionalParams;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.tele2.autoct.dto.AdditionalParamDto;
import ru.tele2.autoct.dto.additionalParams.NotifDto;
import ru.tele2.autoct.jpa.entity.additionalParams.NotifEntity;

@Mapper(componentModel = "spring")
public interface NotifMapper {

    NotifDto convert(NotifEntity notif);

    NotifEntity convert(NotifDto notif);

    @Mappings({
            @Mapping(target="notifId", source="param.paramId"),
            @Mapping(target="notifName", source="param.paramValue")
    })
    NotifDto convert(AdditionalParamDto param);
}
