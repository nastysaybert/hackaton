package ru.tele2.autoct.jpa.repository.dictionaries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tele2.autoct.jpa.entity.dictionaries.AbonDictionaryEntity;
import ru.tele2.autoct.jpa.entity.dictionaries.CheckDictionaryEntity;
import java.util.List;

@Repository
public interface CheckDictionaryRepository extends JpaRepository<CheckDictionaryEntity, Long> {
    List<CheckDictionaryEntity> findAll();

    List<CheckDictionaryEntity> findAllByAbonDictsEquals(AbonDictionaryEntity abonDict);
}