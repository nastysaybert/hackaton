package ru.saybert.hackaton.services.additionalParams;

import ru.saybert.hackaton.dto.additionalParams.ClientTypeDto;
import java.util.List;

public interface ClientTypeService {

    List<ClientTypeDto> getAll();

    ClientTypeDto getById(String id);

    void deleteAll();

    boolean save(ClientTypeDto clientTypeDto);
}
