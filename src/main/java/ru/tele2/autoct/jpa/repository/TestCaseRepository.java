package ru.tele2.autoct.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tele2.autoct.jpa.entity.TestCaseEntity;
import ru.tele2.autoct.jpa.entity.dictionaries.ProjectEntity;

import java.util.List;

@Repository
public interface TestCaseRepository extends JpaRepository<TestCaseEntity, Long> {
    List<TestCaseEntity> findAll();
    TestCaseEntity getByNameAndDelDateIsNull(String name);
    TestCaseEntity getById(Long id);
    List<TestCaseEntity> getAllByTemplateIsTrueAndDelDateIsNull();
    List<TestCaseEntity> getAllByTemplateIsFalseAndDelDateIsNull();
    List<TestCaseEntity> getAllByDelDateIsNotNull();
    List<TestCaseEntity> getAllByProject(ProjectEntity project);
}