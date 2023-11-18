package ru.saybert.hackaton.jpa.repository.additionalParams;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.saybert.hackaton.jpa.entity.additionalParams.BranchEntity;
import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<BranchEntity, String> {
    List<BranchEntity> findAll();

    BranchEntity getByBranchId(String id);
}
