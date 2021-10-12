package ru.tele2.autoct.jpa.repository.additionalParams;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tele2.autoct.jpa.entity.additionalParams.AuthLevelEntity;

import java.util.List;

public interface AuthLevelRepository extends JpaRepository<AuthLevelEntity, String> {
    List<AuthLevelEntity> findAll();
}
