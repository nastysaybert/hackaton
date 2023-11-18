package ru.saybert.hackaton.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.saybert.hackaton.jpa.entity.AbonActionEntity;
import ru.saybert.hackaton.jpa.entity.BTEActionEntity;
import ru.saybert.hackaton.jpa.entity.CheckActionEntity;

import java.util.List;

@Repository
public interface BTEActionRepository extends JpaRepository<BTEActionEntity, Long> {
    List<BTEActionEntity> findAll();

    List<BTEActionEntity> getAllByAbonAction(AbonActionEntity entity);

    List<BTEActionEntity> getAllByCheckAction(CheckActionEntity entity);
}