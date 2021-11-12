package ru.tele2.autoct.services.additionalParams;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tele2.autoct.dto.additionalParams.ZoneDto;
import ru.tele2.autoct.jpa.entity.additionalParams.ServEntity;
import ru.tele2.autoct.jpa.entity.additionalParams.ZoneEntity;
import ru.tele2.autoct.jpa.repository.additionalParams.ZoneRepository;
import ru.tele2.autoct.mappers.additionalParams.ZoneMapper;
import ru.tele2.autoct.views.components.additionalParams.ZoneForm;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ZoneServiceImpl implements ZoneService{

    private final ZoneRepository zoneRepository;
    private final ZoneMapper zoneMapper;

    @Override
    public List<ZoneDto> getAll() {
        List<ZoneDto> zoneDtoList = new ArrayList<>();
        List<ZoneEntity> zoneEntityList = zoneRepository.findAll();
        for (ZoneEntity zoneEntity: zoneEntityList) {
            zoneDtoList.add(zoneMapper.convert(zoneEntity));
        }
        return zoneDtoList;
    }

    public ZoneDto getById(String id){
        return zoneMapper.convert(zoneRepository.getByZoneId(id));
    }

    @Override
    public void deleteAll() {
        zoneRepository.deleteAll();
    }

    @Override
    public boolean save(ZoneDto zoneDto) {
        ZoneEntity entity = zoneMapper.convert(zoneDto);
        zoneRepository.save(entity);
        return true;
    }


}
