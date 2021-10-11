package ru.tele2.autoct.jpa.repository.additionalParams;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tele2.autoct.jpa.entity.additionalParams.BranchEntity;
import java.util.List;

public interface BranchRepository extends JpaRepository<BranchEntity, Long> {
    List<BranchEntity> findAll();
}
