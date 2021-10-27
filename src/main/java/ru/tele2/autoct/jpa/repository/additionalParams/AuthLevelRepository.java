package ru.tele2.autoct.jpa.repository.additionalParams;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tele2.autoct.dto.additionalParams.AuthLevelDto;
import ru.tele2.autoct.jpa.entity.additionalParams.AuthLevelEntity;

import java.util.List;

@Repository
public interface AuthLevelRepository extends JpaRepository<AuthLevelEntity, String> {
    List<AuthLevelEntity> findAll();

    AuthLevelEntity getByAuthLevelId(String id);
}
