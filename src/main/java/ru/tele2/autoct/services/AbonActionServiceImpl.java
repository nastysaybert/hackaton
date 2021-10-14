package ru.tele2.autoct.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tele2.autoct.dto.AbonActionDto;
import ru.tele2.autoct.jpa.entity.AbonActionEntity;
import ru.tele2.autoct.jpa.repository.AbonActionRepository;
import ru.tele2.autoct.mappers.AbonActionMapper;

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
