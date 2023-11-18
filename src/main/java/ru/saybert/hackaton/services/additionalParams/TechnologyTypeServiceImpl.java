package ru.saybert.hackaton.services.additionalParams;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.saybert.hackaton.dto.additionalParams.TechnologyTypeDto;
import ru.saybert.hackaton.jpa.entity.additionalParams.TechnologyTypeEntity;
import ru.saybert.hackaton.jpa.repository.additionalParams.TechnologyTypeRepository;
import ru.saybert.hackaton.mappers.additionalParams.TechnologyTypeMapper;

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
