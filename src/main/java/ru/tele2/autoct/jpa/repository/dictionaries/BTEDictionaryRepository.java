package ru.tele2.autoct.jpa.repository.dictionaries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.tele2.autoct.jpa.entity.dictionaries.BTEDictionaryEntity;
import java.util.List;

@Repository
public interface BTEDictionaryRepository extends JpaRepository<BTEDictionaryEntity, Long> {

        List<BTEDictionaryEntity> findAll();
}
