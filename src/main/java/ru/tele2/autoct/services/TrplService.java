package ru.tele2.autoct.services;

import ru.tele2.autoct.dto.additionalParams.TrplDto;

import java.util.List;

public interface TrplService {

    boolean save(TrplDto trplDto);

    void delete(TrplDto trplDto);

    void deleteAll ();

    List<TrplDto> getAll();
}
