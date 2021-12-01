package ru.tele2.autoct.jpa.repository.dictionaries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tele2.autoct.jpa.entity.dictionaries.ProjectEntity;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

    List<ProjectEntity> findAll();
    ProjectEntity getByProjectId(Long id);
    List<ProjectEntity> getAllByProjectNameLike(String text);

}
