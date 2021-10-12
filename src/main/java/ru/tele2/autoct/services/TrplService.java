package ru.tele2.autoct.services;

import ru.tele2.autoct.dto.additionalParams.TrplDto;

public interface TrplService {

    boolean save(TrplDto trplDto);

    void delete(TrplDto trplDto);

    void deleteAll ();
}
