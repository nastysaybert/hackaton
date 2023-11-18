package ru.saybert.hackaton.services.additionalParams;

import ru.saybert.hackaton.dto.additionalParams.ZoneDto;
import java.util.List;

public interface ZoneService {

    List<ZoneDto> getAll();

    ZoneDto getById(String id);

    void deleteAll();

    boolean save(ZoneDto zoneDto);
}
