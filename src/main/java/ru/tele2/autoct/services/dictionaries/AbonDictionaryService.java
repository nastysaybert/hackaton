package ru.tele2.autoct.services.dictionaries;

import org.springframework.transaction.annotation.Transactional;
import ru.tele2.autoct.dto.dictionaries.AbonDictionaryDto;
import java.util.List;

public interface AbonDictionaryService {

    List<AbonDictionaryDto> getAll();
}
