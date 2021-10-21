package ru.tele2.autoct.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tele2.autoct.jpa.entity.InitialDataEntity;

import java.util.List;

@Repository
public interface InitialDataRepository extends JpaRepository<InitialDataEntity, Long> {

    List<InitialDataEntity> findAll();
}