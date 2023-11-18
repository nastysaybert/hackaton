package ru.saybert.hackaton.services.additionalParams;

import ru.saybert.hackaton.dto.additionalParams.ServDto;

import java.util.List;

public interface ServService {

    boolean save(ServDto servDto);

    void delete(ServDto servDto);

    void deleteAll();

    List<ServDto> getAll();

    ServDto getById(String id);
}
