package ru.saybert.hackaton.jpa.repository.dictionaries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.saybert.hackaton.jpa.entity.dictionaries.AbonDictionaryEntity;
import ru.saybert.hackaton.jpa.entity.dictionaries.CheckDictionaryEntity;
import java.util.List;

@Repository
public interface CheckDictionaryRepository extends JpaRepository<CheckDictionaryEntity, Long> {
    List<CheckDictionaryEntity> findAll();

    List<CheckDictionaryEntity> findAllByAbonDictsEquals(AbonDictionaryEntity abonDict);
}