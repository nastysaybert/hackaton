package ru.tele2.autoct.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tele2.autoct.dto.additionalParams.AuthLevelDto;
import ru.tele2.autoct.jpa.entity.additionalParams.AuthLevelEntity;
import ru.tele2.autoct.jpa.repository.additionalParams.AuthLevelRepository;
import ru.tele2.autoct.mappers.additionalParams.AuthLevelMapper;

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
}
