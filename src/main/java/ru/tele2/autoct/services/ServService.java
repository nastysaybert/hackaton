package ru.tele2.autoct.services;

import ru.tele2.autoct.dto.additionalParams.ServDto;

public interface ServService {

    boolean save(ServDto servDto);

    void delete(ServDto servDto);

    void deleteAll();
}
