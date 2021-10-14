package ru.tele2.autoct.services.dictionaries;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tele2.autoct.dto.dictionaries.AbonDictionaryDto;
import ru.tele2.autoct.dto.dictionaries.CheckDictionaryDto;
import java.util.List;


public interface CheckDictionaryService {

    List<CheckDictionaryDto> getAllByAbonDict(AbonDictionaryDto abonDictionaryDto);
}
