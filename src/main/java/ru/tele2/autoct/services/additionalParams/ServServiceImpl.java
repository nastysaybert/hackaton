package ru.tele2.autoct.services.additionalParams;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tele2.autoct.dto.additionalParams.ServDto;
import ru.tele2.autoct.jpa.entity.additionalParams.ServEntity;
import ru.tele2.autoct.jpa.repository.additionalParams.ServRepository;
import ru.tele2.autoct.mappers.additionalParams.ServMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ServServiceImpl implements ServService{

    private final ServRepository servRepository;
    private final ServMapper servMapper;

    public boolean save(ServDto servDto) {
        ServEntity entity = servMapper.convert(servDto);
        servRepository.save(entity);
        return true;
    }

    public void delete(ServDto servDto) {
        ServEntity entity = servMapper.convert(servDto);
        servRepository.delete(entity);
    }

    public void deleteAll(){
        servRepository.deleteAll();
    }

    public List<ServDto> getAll(){
        List<ServDto> servDtoList = new ArrayList<>();
        List<ServEntity> servEntityList = servRepository.findAll();
        for (ServEntity servEntity: servEntityList) {
            servDtoList.add(servMapper.convert(servEntity));
        }
        return servDtoList;
    }
}
