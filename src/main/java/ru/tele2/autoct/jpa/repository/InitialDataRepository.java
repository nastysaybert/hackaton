package ru.tele2.autoct.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tele2.autoct.jpa.entity.InitialDataEntity;

import java.util.List;

public interface InitialDataRepository extends JpaRepository<InitialDataEntity, Long> {
    List<InitialDataEntity> findAll();

    List<InitialDataEntity> getAllByTestCaseId(Long testCaseId);
}