package ru.tele2.autoct.jpa.repository.additionalParams;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tele2.autoct.jpa.entity.additionalParams.ClientTypeEntity;
import java.util.List;

public interface ClientTypeRepository extends JpaRepository<ClientTypeEntity, String> {

    List<ClientTypeEntity> findAll();
    ClientTypeEntity getByClientTypeId(String id);
    void deleteAll();
}
