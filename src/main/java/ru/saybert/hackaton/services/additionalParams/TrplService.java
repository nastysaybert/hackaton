package ru.saybert.hackaton.services.additionalParams;

import ru.saybert.hackaton.dto.additionalParams.TrplDto;

import java.util.List;

public interface TrplService {

    boolean save(TrplDto trplDto);

    void delete(TrplDto trplDto);

    void deleteAll ();

    List<TrplDto> getAll();

    TrplDto getById(String id, String name);

}
