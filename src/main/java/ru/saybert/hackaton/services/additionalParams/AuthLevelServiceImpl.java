package ru.saybert.hackaton.services.additionalParams;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.saybert.hackaton.dto.additionalParams.AuthLevelDto;
import ru.saybert.hackaton.jpa.entity.additionalParams.AuthLevelEntity;
import ru.saybert.hackaton.jpa.repository.additionalParams.AuthLevelRepository;
import ru.saybert.hackaton.mappers.additionalParams.AuthLevelMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AuthLevelServiceImpl implements AuthLevelService {

    private final AuthLevelRepository authLevelRepository;
    private final AuthLevelMapper authLevelMapper;

    @Override
    public List<AuthLevelDto> getAll() {
        List<AuthLevelDto> authLevelDtoList = new ArrayList<>();
        List<AuthLevelEntity> authLevelEntityList = authLevelRepository.findAll();
        for (AuthLevelEntity authLevelEntity: authLevelEntityList) {
            authLevelDtoList.add(authLevelMapper.convert(authLevelEntity));
        }
        return authLevelDtoList;
    }

    public AuthLevelDto getById(String id){
        return authLevelMapper.convert(authLevelRepository.getByAuthLevelId(id));
    }
}
