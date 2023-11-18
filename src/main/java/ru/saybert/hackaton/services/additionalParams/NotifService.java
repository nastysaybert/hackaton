package ru.saybert.hackaton.services.additionalParams;

import ru.saybert.hackaton.dto.additionalParams.NotifDto;
import java.util.List;

public interface NotifService {

    List<NotifDto> getAll();

    NotifDto getById(String id);
}
