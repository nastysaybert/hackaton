package ru.tele2.autoct.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tele2.autoct.dto.additionalParams.NotifDto;
import ru.tele2.autoct.dto.additionalParams.ServDto;
import ru.tele2.autoct.jpa.entity.additionalParams.NotifEntity;
import ru.tele2.autoct.jpa.entity.additionalParams.ServEntity;
import ru.tele2.autoct.jpa.repository.additionalParams.NotifRepository;
import ru.tele2.autoct.mappers.additionalParams.NotifMapper;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class NotifServiceImpl implements NotifService {

    private final NotifMapper notifMapper;
    private final NotifRepository notifRepository;

    @Override
    public List<NotifDto> getAll() {
        List<NotifDto> notifDtoList = new ArrayList<>();
        List<NotifEntity> notifEntityList = notifRepository.findAll();
        for (NotifEntity notifEntity: notifEntityList) {
            notifDtoList.add(notifMapper.convert(notifEntity));
        }
        return notifDtoList;
    }
}
