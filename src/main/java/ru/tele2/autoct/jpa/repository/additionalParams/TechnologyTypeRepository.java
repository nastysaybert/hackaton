package ru.tele2.autoct.jpa.repository.additionalParams;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tele2.autoct.jpa.entity.additionalParams.TechnologyTypeEntity;
import java.util.List;

public interface TechnologyTypeRepository  extends JpaRepository<TechnologyTypeEntity, String> {

    List<TechnologyTypeEntity> findAll();
    TechnologyTypeEntity getByTechnologyTypeId(String id);
}
