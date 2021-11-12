package ru.tele2.autoct.services.additionalParams;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tele2.autoct.dto.additionalParams.ActivationMethodDto;
import ru.tele2.autoct.jpa.entity.additionalParams.ActivationMethodEntity;
import ru.tele2.autoct.jpa.repository.additionalParams.ActivationMethodRepository;
import ru.tele2.autoct.mappers.additionalParams.ActivationMethodMapper;
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
