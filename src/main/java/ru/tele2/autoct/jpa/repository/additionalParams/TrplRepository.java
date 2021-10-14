package ru.tele2.autoct.jpa.repository.additionalParams;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tele2.autoct.jpa.entity.additionalParams.TrplEntity;
import java.util.List;

@Repository
public interface TrplRepository extends JpaRepository<TrplEntity, String> {

    List<TrplEntity> findAll();

    TrplEntity getByTrplId(String trplId);
}
