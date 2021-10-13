package ru.tele2.autoct.services;

import ru.tele2.autoct.dto.additionalParams.NotifDto;
import java.util.List;

public interface NotifService {

    List<NotifDto> getAll();
}
