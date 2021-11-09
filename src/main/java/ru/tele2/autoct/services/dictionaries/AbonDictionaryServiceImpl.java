package ru.tele2.autoct.services.dictionaries;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tele2.autoct.dto.dictionaries.AbonDictionaryDto;
import ru.tele2.autoct.jpa.entity.dictionaries.AbonDictionaryEntity;
import ru.tele2.autoct.jpa.repository.dictionaries.AbonDictionaryRepository;
import ru.tele2.autoct.mappers.dictionaries.AbonDictionaryMapper;

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
