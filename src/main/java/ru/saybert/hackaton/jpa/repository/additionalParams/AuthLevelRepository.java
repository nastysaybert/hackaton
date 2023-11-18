package ru.saybert.hackaton.jpa.repository.additionalParams;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.saybert.hackaton.jpa.entity.additionalParams.AuthLevelEntity;

import java.util.List;

@Repository
public interface AuthLevelRepository extends JpaRepository<AuthLevelEntity, String> {

    List<AuthLevelEntity> findAll();
    AuthLevelEntity getByAuthLevelId(String id);
}
