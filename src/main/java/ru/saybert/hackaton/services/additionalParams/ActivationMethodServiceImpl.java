package ru.saybert.hackaton.services.additionalParams;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.saybert.hackaton.dto.additionalParams.ActivationMethodDto;
import ru.saybert.hackaton.jpa.entity.additionalParams.ActivationMethodEntity;
import ru.saybert.hackaton.jpa.repository.additionalParams.ActivationMethodRepository;
import ru.saybert.hackaton.mappers.additionalParams.ActivationMethodMapper;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ActivationMethodServiceImpl implements ActivationMethodService{

    private final ActivationMethodRepository activationMethodRepository;
    private final ActivationMethodMapper activationMethodMapper;

    @Override
    public List<ActivationMethodDto> getAll() {
        List<ActivationMethodDto> activationMethodDtoList = new ArrayList<>();
        List<ActivationMethodEntity> activationMethodEntityList = activationMethodRepository.findAll();
        for (ActivationMethodEntity activationMethodEntity: activationMethodEntityList) {
            activationMethodDtoList.add(activationMethodMapper.convert(activationMethodEntity));
        }
        return activationMethodDtoList;
    }

    public ActivationMethodDto getById(String id){
        return activationMethodMapper.convert(activationMethodRepository.getByMethod(id));
    }
}
