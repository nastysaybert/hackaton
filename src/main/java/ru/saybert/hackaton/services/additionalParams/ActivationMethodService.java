package ru.saybert.hackaton.services.additionalParams;

import ru.saybert.hackaton.dto.additionalParams.ActivationMethodDto;
import java.util.List;

public interface ActivationMethodService {

    List<ActivationMethodDto> getAll();

    ActivationMethodDto getById(String id);
}
