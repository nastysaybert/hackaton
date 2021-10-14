package ru.tele2.autoct.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tele2.autoct.jpa.entity.TestCaseStepEntity;

import java.util.List;

@Repository
public interface TestCaseStepRepository extends JpaRepository<TestCaseStepEntity, Long> {

    List<TestCaseStepEntity> findAll();

    List<TestCaseStepEntity> getAllByTestCaseId(Long testCaseId);
}