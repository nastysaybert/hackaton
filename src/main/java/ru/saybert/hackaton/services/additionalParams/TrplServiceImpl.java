package ru.saybert.hackaton.services.additionalParams;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.saybert.hackaton.dto.additionalParams.TrplDto;
import ru.saybert.hackaton.jpa.entity.additionalParams.TrplEntity;
import ru.saybert.hackaton.jpa.repository.additionalParams.TrplRepository;
import ru.saybert.hackaton.mappers.additionalParams.TrplMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TrplServiceImpl implements TrplService {

    private final TrplRepository trplRepository;
    private final TrplMapper trplMapper;

    public boolean save(TrplDto trplDto) {
        TrplEntity entity = trplMapper.convert(trplDto);
        trplRepository.save(entity);
        return true;
    }

    public void delete(TrplDto trplDto) {
        TrplEntity entity = trplMapper.convert(trplDto);
        trplRepository.delete(entity);
    }

    public void deleteAll (){
        trplRepository.deleteAll();
    }

    public List<TrplDto> getAll(){
        List<TrplDto> trplDtoList = new ArrayList<>();
        List<TrplEntity> servEntityList = trplRepository.findAll();
        for (TrplEntity trplEntity: servEntityList) {
            trplDtoList.add(trplMapper.convert(trplEntity));
        }
        return trplDtoList;
    }

    public TrplDto getById(String id, String name){
//        return trplMapper.convert(trplRepository.getByTrplId(id));
        return trplMapper.convert(trplRepository.getByTrplIdContainingAndTrplName(id, name));

    }

}
