package ru.tele2.autoct.services.dictionaries;

import ru.tele2.autoct.dto.dictionaries.ProjectDto;
import java.util.List;


public interface ProjectService {
    List<ProjectDto> getAll();
    ProjectDto getById(Long id);
    void deleteAll();
    void save(ProjectDto projectDto);
    List<ProjectDto> getAllByNameLike(String text);
}
