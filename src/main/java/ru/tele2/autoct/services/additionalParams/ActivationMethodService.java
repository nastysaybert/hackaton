package ru.tele2.autoct.services.additionalParams;

import ru.tele2.autoct.dto.additionalParams.ActivationMethodDto;
import java.util.List;

public interface ActivationMethodService {

    List<ActivationMethodDto> getAll();

    ActivationMethodDto getById(String id);
}
