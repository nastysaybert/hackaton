package ru.saybert.hackaton.jpa.repository.dictionaries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.saybert.hackaton.jpa.entity.dictionaries.BTEDictionaryEntity;
import java.util.List;

@Repository
public interface BTEDictionaryRepository extends JpaRepository<BTEDictionaryEntity, Long> {

        List<BTEDictionaryEntity> findAll();
}
