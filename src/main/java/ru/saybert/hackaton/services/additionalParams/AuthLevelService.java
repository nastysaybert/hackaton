package ru.saybert.hackaton.services.additionalParams;

import ru.saybert.hackaton.dto.additionalParams.AuthLevelDto;

import java.util.List;

public interface AuthLevelService {

    List<AuthLevelDto> getAll();

    AuthLevelDto getById(String id);

}
