package ru.saybert.hackaton.services;

import ru.saybert.hackaton.dto.AbonActionDto;
import java.util.List;

public interface AbonActionService {
    boolean save(AbonActionDto abonActionDto);

    void delete(AbonActionDto abonActionDto);

    List<AbonActionDto> getAll();
}
