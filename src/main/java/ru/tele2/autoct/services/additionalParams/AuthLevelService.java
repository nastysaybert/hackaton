package ru.tele2.autoct.services.additionalParams;

import ru.tele2.autoct.dto.additionalParams.AuthLevelDto;

import java.util.List;

public interface AuthLevelService {

    List<AuthLevelDto> getAll();

    AuthLevelDto getById(String id);

}
