package ru.tele2.autoct.jpa.repository.dictionaries;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tele2.autoct.jpa.entity.dictionaries.BTEDictionaryEntity;
import java.util.List;

public interface BTEDictionaryRepository extends JpaRepository<BTEDictionaryEntity, Long> {
        List<BTEDictionaryEntity> findAll();
}
