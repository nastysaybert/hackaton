package ru.saybert.hackaton.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.saybert.hackaton.jpa.entity.CheckActionEntity;
import java.util.List;

@Repository
public interface CheckActionRepository extends JpaRepository<CheckActionEntity, Long> {
    List<CheckActionEntity> findAll();

    List<CheckActionEntity> getAllByTestCaseStepId(Long testCaseStepId);

}