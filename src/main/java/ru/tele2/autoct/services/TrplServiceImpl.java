package ru.tele2.autoct.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tele2.autoct.dto.additionalParams.TrplDto;
import ru.tele2.autoct.jpa.entity.additionalParams.TrplEntity;
import ru.tele2.autoct.jpa.repository.additionalParams.TrplRepository;
import ru.tele2.autoct.mappers.additionalParams.TrplMapper;

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

}
