package ru.saybert.hackaton.services.additionalParams;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.saybert.hackaton.dto.additionalParams.ClientTypeDto;
import ru.saybert.hackaton.jpa.entity.additionalParams.ClientTypeEntity;
import ru.saybert.hackaton.jpa.repository.additionalParams.ClientTypeRepository;
import ru.saybert.hackaton.mappers.additionalParams.ClientTypeMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ClientTypeServiceImpl implements ClientTypeService{

    private final ClientTypeRepository clientTypeRepository;
    private final ClientTypeMapper clientTypeMapper;

    @Override
    public List<ClientTypeDto> getAll() {
        List<ClientTypeDto> clientTypeDtoList = new ArrayList<>();
        List<ClientTypeEntity> clientTypeEntityList = clientTypeRepository.findAll();
        for (ClientTypeEntity clientTypeEntity: clientTypeEntityList) {
            clientTypeDtoList.add(clientTypeMapper.convert(clientTypeEntity));
        }
        return clientTypeDtoList;
    }

    public ClientTypeDto getById(String id){
        return clientTypeMapper.convert(clientTypeRepository.getByClientTypeId(id));
    }

    @Override
    public void deleteAll() {
        clientTypeRepository.deleteAll();
    }

    @Override
    public boolean save(ClientTypeDto clientTypeDto) {
        ClientTypeEntity entity = clientTypeMapper.convert(clientTypeDto);
        clientTypeRepository.save(entity);
        return true;
    }
}
