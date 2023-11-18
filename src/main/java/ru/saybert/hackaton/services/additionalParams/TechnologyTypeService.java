package ru.saybert.hackaton.services.additionalParams;

import ru.saybert.hackaton.dto.additionalParams.TechnologyTypeDto;
import java.util.List;

public interface TechnologyTypeService {

    List<TechnologyTypeDto> getAll();

    TechnologyTypeDto getById(String id);
}
