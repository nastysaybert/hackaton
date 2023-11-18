package ru.saybert.hackaton.services.dictionaries;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.saybert.hackaton.dto.dictionaries.AbonDictionaryDto;
import ru.saybert.hackaton.dto.dictionaries.CheckDictionaryDto;
import ru.saybert.hackaton.jpa.entity.dictionaries.CheckDictionaryEntity;
import ru.saybert.hackaton.jpa.repository.dictionaries.CheckDictionaryRepository;
import ru.saybert.hackaton.mappers.dictionaries.AbonDictionaryMapper;
import ru.saybert.hackaton.mappers.dictionaries.CheckDictionaryMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CheckDictionaryServiceImpl implements CheckDictionaryService {

    private final CheckDictionaryRepository checkDictionaryRepository;
    private final AbonDictionaryMapper abonDictionaryMapper;
    private final CheckDictionaryMapper checkDictionaryMapper;

    @Transactional
    public List<CheckDictionaryDto> getAllByAbonDict(AbonDictionaryDto abonDictionaryDto){
        List<CheckDictionaryEntity> entityList
                = checkDictionaryRepository.findAllByAbonDictsEquals(abonDictionaryMapper.convert(abonDictionaryDto));
        List<CheckDictionaryDto> result = new ArrayList<>();
        for (CheckDictionaryEntity entity : entityList){
            result.add(checkDictionaryMapper.convert(entity));
        }
        return result;
    }
}
