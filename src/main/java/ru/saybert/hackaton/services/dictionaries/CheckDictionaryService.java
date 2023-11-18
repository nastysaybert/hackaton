package ru.saybert.hackaton.services.dictionaries;

import ru.saybert.hackaton.dto.dictionaries.AbonDictionaryDto;
import ru.saybert.hackaton.dto.dictionaries.CheckDictionaryDto;
import java.util.List;


public interface CheckDictionaryService {

    List<CheckDictionaryDto> getAllByAbonDict(AbonDictionaryDto abonDictionaryDto);
}
