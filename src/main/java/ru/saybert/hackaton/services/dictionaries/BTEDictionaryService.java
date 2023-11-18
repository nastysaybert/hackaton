package ru.saybert.hackaton.services.dictionaries;

import ru.saybert.hackaton.dto.dictionaries.BTEDictionaryDto;
import ru.saybert.hackaton.enums.ParamType;

import java.util.List;

public interface BTEDictionaryService {

    List<ParamType> parseParamTypes(BTEDictionaryDto bteDictionaryDto);
}
