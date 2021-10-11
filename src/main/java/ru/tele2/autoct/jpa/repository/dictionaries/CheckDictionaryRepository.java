package ru.tele2.autoct.jpa.repository.dictionaries;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tele2.autoct.jpa.entity.dictionaries.CheckDictionaryEntity;
import java.util.List;

public interface CheckDictionaryRepository extends JpaRepository<CheckDictionaryEntity, Long> {
    List<CheckDictionaryEntity> findAll();
}