package ru.saybert.hackaton.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.saybert.hackaton.jpa.entity.AbonActionEntity;
import ru.saybert.hackaton.dto.AbonActionDto;
import ru.saybert.hackaton.jpa.repository.AbonActionRepository;
import ru.saybert.hackaton.mappers.AbonActionMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AbonActionServiceImpl implements AbonActionService{

    private final AbonActionRepository abonActionRepository;
    private final AbonActionMapper abonActionMapper;

    public boolean save(AbonActionDto abonActionDto){
        AbonActionEntity entity = abonActionMapper.convert(abonActionDto);
        abonActionRepository.save(entity);
        return true;
    }

    public void delete(AbonActionDto abonActionDto){
        AbonActionEntity entity = abonActionMapper.convert(abonActionDto);
        abonActionRepository.delete(entity);
    }

    @Transactional
    public List<AbonActionDto> getAll(){
        List<AbonActionEntity> abonActionEntityList = abonActionRepository.findAll();
        List<AbonActionDto> result = new ArrayList<>();
        for(AbonActionEntity abonActionEntity:abonActionEntityList){
            result.add(abonActionMapper.convert(abonActionEntity));
        }
        return result;
    }
}
