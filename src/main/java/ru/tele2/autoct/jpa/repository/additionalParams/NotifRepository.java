package ru.tele2.autoct.jpa.repository.additionalParams;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tele2.autoct.jpa.entity.additionalParams.NotifEntity;
import java.util.List;

@Repository
public interface NotifRepository extends JpaRepository<NotifEntity, String> {
    List<NotifEntity> findAll();

    NotifEntity getByNotifId (String id);
}
