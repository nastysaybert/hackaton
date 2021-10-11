package ru.tele2.autoct.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tele2.autoct.jpa.entity.CheckActionEntity;
import java.util.List;

public interface CheckActionRepository extends JpaRepository<CheckActionEntity, Long> {
    List<CheckActionEntity> findAll();

    List<CheckActionEntity> getAllByTestCaseStepId(Long testCaseStepId);
}