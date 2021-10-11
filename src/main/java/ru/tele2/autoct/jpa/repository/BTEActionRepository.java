package ru.tele2.autoct.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tele2.autoct.jpa.entity.BTEActionEntity;

import java.util.List;

public interface BTEActionRepository extends JpaRepository<BTEActionEntity, Long> {
    List<BTEActionEntity> findAll();
}