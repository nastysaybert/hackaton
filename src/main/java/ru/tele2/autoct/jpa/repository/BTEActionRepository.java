package ru.tele2.autoct.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tele2.autoct.jpa.entity.BTEActionEntity;

import java.util.List;

@Repository
public interface BTEActionRepository extends JpaRepository<BTEActionEntity, Long> {
    List<BTEActionEntity> findAll();
}