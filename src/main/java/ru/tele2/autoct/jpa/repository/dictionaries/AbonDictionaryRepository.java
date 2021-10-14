package ru.tele2.autoct.jpa.repository.dictionaries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tele2.autoct.jpa.entity.dictionaries.AbonDictionaryEntity;

import java.util.List;

@Repository
public interface AbonDictionaryRepository extends JpaRepository<AbonDictionaryEntity, Long> {
    List<AbonDictionaryEntity> findAll();
}