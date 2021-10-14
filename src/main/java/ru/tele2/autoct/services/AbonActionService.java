package ru.tele2.autoct.services;

import ru.tele2.autoct.dto.AbonActionDto;
import java.util.List;

public interface AbonActionService {
    boolean save(AbonActionDto abonActionDto);

    void delete(AbonActionDto abonActionDto);

    List<AbonActionDto> getAll();
}
