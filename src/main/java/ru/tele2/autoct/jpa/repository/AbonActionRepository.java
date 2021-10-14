package ru.tele2.autoct.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tele2.autoct.jpa.entity.AbonActionEntity;

import java.util.List;

@Repository
public interface AbonActionRepository extends JpaRepository<AbonActionEntity, Long> {

    List<AbonActionEntity> findAll();

    AbonActionEntity getByTestCaseStepId(Long testCaseStepId);

}