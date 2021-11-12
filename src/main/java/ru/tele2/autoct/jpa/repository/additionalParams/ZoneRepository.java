package ru.tele2.autoct.jpa.repository.additionalParams;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tele2.autoct.jpa.entity.additionalParams.ZoneEntity;

import java.util.List;

@Repository
public interface ZoneRepository extends JpaRepository<ZoneEntity, String> {
    List<ZoneEntity> findAll();
    ZoneEntity getByZoneId (String zoneId);
    void deleteAll();
}
