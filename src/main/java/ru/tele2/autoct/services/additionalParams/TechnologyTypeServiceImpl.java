package ru.tele2.autoct.services.additionalParams;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tele2.autoct.dto.additionalParams.TechnologyTypeDto;
import ru.tele2.autoct.jpa.entity.additionalParams.TechnologyTypeEntity;
import ru.tele2.autoct.jpa.repository.additionalParams.TechnologyTypeRepository;
import ru.tele2.autoct.mappers.additionalParams.TechnologyTypeMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TechnologyTypeServiceImpl implements TechnologyTypeService {

    private final TechnologyTypeRepository technologyTypeRepository;
    private final TechnologyTypeMapper technologyTypeMapper;

    @Override
    public List<TechnologyTypeDto> getAll() {
        List<TechnologyTypeDto> technologyTypeDtoList = new ArrayList<>();
        List<TechnologyTypeEntity> technologyTypeEntityList = technologyTypeRepository.findAll();
        for (TechnologyTypeEntity technologyTypeEntity: technologyTypeEntityList) {
            technologyTypeDtoList.add(technologyTypeMapper.convert(technologyTypeEntity));
        }
        return technologyTypeDtoList;
    }

    public TechnologyTypeDto getById(String id){
        return technologyTypeMapper.convert(technologyTypeRepository.getByTechnologyTypeId(id));
    }
}
