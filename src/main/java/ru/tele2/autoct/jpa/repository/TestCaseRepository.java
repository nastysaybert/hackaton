package ru.tele2.autoct.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tele2.autoct.jpa.entity.TestCaseEntity;
import ru.tele2.autoct.jpa.entity.TestCaseStepEntity;

import java.util.List;

public interface TestCaseRepository extends JpaRepository<TestCaseEntity, Long> {
    List<TestCaseEntity> findAll();
}