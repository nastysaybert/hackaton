package ru.saybert.hackaton.mappers.additionalParams;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.saybert.hackaton.dto.AdditionalParamDto;
import ru.saybert.hackaton.dto.additionalParams.NotifDto;
import ru.saybert.hackaton.jpa.entity.additionalParams.NotifEntity;

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
