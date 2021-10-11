package ru.tele2.autoct.jpa.repository.additionalParams;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tele2.autoct.jpa.entity.additionalParams.TrplEntity;
import java.util.List;

public interface TrplRepository extends JpaRepository<TrplEntity, Long> {
    List<TrplEntity> findAll();
}
