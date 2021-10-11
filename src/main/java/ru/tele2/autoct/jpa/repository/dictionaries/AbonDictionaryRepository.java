package ru.tele2.autoct.jpa.repository.dictionaries;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tele2.autoct.jpa.entity.dictionaries.AbonDictionaryEntity;

import java.util.List;

public interface AbonDictionaryRepository extends JpaRepository<AbonDictionaryEntity, Long> {
    List<AbonDictionaryEntity> findAll();
}