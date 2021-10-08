package ru.tele2.autoct.mappers.additionalParams;

import org.mapstruct.Mapper;
import ru.tele2.autoct.dto.additionalParams.NotifDto;
import ru.tele2.autoct.jpa.entity.additionalParams.NotifEntity;

@Mapper(componentModel = "spring")
public interface NotifMapper {

    NotifDto convert(NotifEntity notif);

    NotifEntity convert(NotifDto notif);
}
