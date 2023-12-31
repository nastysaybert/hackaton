package ru.saybert.hackaton.services.additionalParams;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.saybert.hackaton.dto.additionalParams.NotifDto;
import ru.saybert.hackaton.jpa.entity.additionalParams.NotifEntity;
import ru.saybert.hackaton.jpa.repository.additionalParams.NotifRepository;
import ru.saybert.hackaton.mappers.additionalParams.NotifMapper;
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

    public NotifDto getById(String id){
        return notifMapper.convert(notifRepository.getByNotifId(id));
    }
}
