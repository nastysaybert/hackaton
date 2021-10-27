package ru.tele2.autoct.services.additionalParams;

import ru.tele2.autoct.dto.additionalParams.NotifDto;
import java.util.List;

public interface NotifService {

    List<NotifDto> getAll();

    NotifDto getById(String id);
}
