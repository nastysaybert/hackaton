package ru.saybert.hackaton.services.dictionaries;

import ru.saybert.hackaton.dto.dictionaries.AbonDictionaryDto;
import java.util.List;

public interface AbonDictionaryService {

    List<AbonDictionaryDto> getAll();
}
