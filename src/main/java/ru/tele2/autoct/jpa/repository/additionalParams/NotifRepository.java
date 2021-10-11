package ru.tele2.autoct.jpa.repository.additionalParams;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tele2.autoct.jpa.entity.additionalParams.NotifEntity;
import java.util.List;

public interface NotifRepository extends JpaRepository<NotifEntity, Long> {
    List<NotifEntity> findAll();
}
