package ru.saybert.hackaton.services.dictionaries;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.saybert.hackaton.dto.dictionaries.AbonDictionaryDto;
import ru.saybert.hackaton.jpa.entity.dictionaries.AbonDictionaryEntity;
import ru.saybert.hackaton.jpa.repository.dictionaries.AbonDictionaryRepository;
import ru.saybert.hackaton.mappers.dictionaries.AbonDictionaryMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AbonDictionaryServiceImpl implements AbonDictionaryService {

    private final AbonDictionaryRepository abonDictionaryRepository;
    private final AbonDictionaryMapper abonDictionaryMapper;

    @Transactional
    public List<AbonDictionaryDto> getAll() {
        List<AbonDictionaryEntity> entityList
                = abonDictionaryRepository.findAll();
        List<AbonDictionaryDto> result = new ArrayList<>();
        for (AbonDictionaryEntity entity : entityList){
            result.add(abonDictionaryMapper.convert(entity));
        }
        return result;
    }




}
