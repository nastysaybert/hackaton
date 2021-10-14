package ru.tele2.autoct.jpa.repository.additionalParams;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tele2.autoct.jpa.entity.additionalParams.ServEntity;
import java.util.List;

@Repository
public interface ServRepository extends JpaRepository<ServEntity, String> {
    List<ServEntity> findAll();
    ServEntity getByServId(String servId);
}
