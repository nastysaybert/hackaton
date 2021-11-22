package ru.tele2.autoct.services.additionalParams;

import ru.tele2.autoct.dto.additionalParams.ClientTypeDto;
import java.util.List;

public interface ClientTypeService {

    List<ClientTypeDto> getAll();

    ClientTypeDto getById(String id);

    void deleteAll();

    boolean save(ClientTypeDto clientTypeDto);
}
