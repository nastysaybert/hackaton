package ru.saybert.hackaton.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.saybert.hackaton.jpa.entity.TestCaseStepEntity;

import java.util.List;

@Repository
public interface TestCaseStepRepository extends JpaRepository<TestCaseStepEntity, Long> {

    List<TestCaseStepEntity> findAll();

    List<TestCaseStepEntity> getAllByTestCaseId(Long testCaseId);
}