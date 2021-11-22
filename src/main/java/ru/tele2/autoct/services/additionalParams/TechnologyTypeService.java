package ru.tele2.autoct.services.additionalParams;

import ru.tele2.autoct.dto.additionalParams.TechnologyTypeDto;
import java.util.List;

public interface TechnologyTypeService {

    List<TechnologyTypeDto> getAll();

    TechnologyTypeDto getById(String id);
}
