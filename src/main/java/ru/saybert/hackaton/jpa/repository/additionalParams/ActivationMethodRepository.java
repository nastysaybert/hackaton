package ru.saybert.hackaton.jpa.repository.additionalParams;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.saybert.hackaton.jpa.entity.additionalParams.ActivationMethodEntity;

import java.util.List;

@Repository
public interface ActivationMethodRepository extends JpaRepository<ActivationMethodEntity, String> {
    List<ActivationMethodEntity> findAll();
    ActivationMethodEntity getByMethod (String method);
}
