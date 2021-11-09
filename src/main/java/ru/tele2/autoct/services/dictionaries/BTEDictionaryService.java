package ru.tele2.autoct.services.dictionaries;

import ru.tele2.autoct.dto.dictionaries.BTEDictionaryDto;
import ru.tele2.autoct.enums.ParamType;

import java.util.List;

public interface BTEDictionaryService {

    List<ParamType> parseParamTypes(BTEDictionaryDto bteDictionaryDto);
}
