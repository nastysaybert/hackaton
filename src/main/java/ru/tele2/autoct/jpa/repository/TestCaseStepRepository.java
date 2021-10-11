package ru.tele2.autoct.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tele2.autoct.jpa.entity.TestCaseStepEntity;

import java.util.List;

public interface TestCaseStepRepository extends JpaRepository<TestCaseStepEntity, Long> {

    List<TestCaseStepEntity> findAll();

    List<TestCaseStepEntity> getAllByTestCaseId(Long testCaseId);
}