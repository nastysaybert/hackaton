package ru.tele2.autoct.services.additionalParams;

import ru.tele2.autoct.dto.additionalParams.ZoneDto;
import java.util.List;

public interface ZoneService {

    List<ZoneDto> getAll();

    ZoneDto getById(String id);

    void deleteAll();

    boolean save(ZoneDto zoneDto);
}
