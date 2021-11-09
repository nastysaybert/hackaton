package ru.tele2.autoct.services.dictionaries;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tele2.autoct.dto.dictionaries.BTEDictionaryDto;
import ru.tele2.autoct.enums.ParamType;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BTEDictionaryServiceImpl implements BTEDictionaryService {

    public List<ParamType> parseParamTypes(BTEDictionaryDto bteDictionaryDto){
        List<ParamType> result = new ArrayList<>();
        String paramTypes = bteDictionaryDto.getParamType();
        int fromIndex = 0;
        int toIndex = paramTypes.indexOf(",", fromIndex);
        while (toIndex != -1){
            result.add(ParamType.valueOf(paramTypes.substring(fromIndex, toIndex)));
            fromIndex = toIndex+1;
            toIndex = paramTypes.indexOf(",", fromIndex);
        }
        result.add(ParamType.valueOf(paramTypes.substring(fromIndex)));
        return result;
    }
}
