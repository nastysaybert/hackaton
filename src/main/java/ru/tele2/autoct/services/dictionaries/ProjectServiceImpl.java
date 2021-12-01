package ru.tele2.autoct.services.dictionaries;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tele2.autoct.dto.dictionaries.ProjectDto;
import ru.tele2.autoct.jpa.entity.dictionaries.ProjectEntity;
import ru.tele2.autoct.jpa.repository.dictionaries.ProjectRepository;
import ru.tele2.autoct.mappers.dictionaries.ProjectMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService{

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    @Override
    public List<ProjectDto> getAll() {
        List<ProjectEntity> entityList = projectRepository.findAll();
        List<ProjectDto> dtoList = new ArrayList<>();
        for (ProjectEntity entity : entityList) {
            dtoList.add(projectMapper.convert(entity));
        }
        return dtoList;
    }

    @Override
    public ProjectDto getById(Long id) {
        return projectMapper.convert(projectRepository.getByProjectId(id));
    }

    @Override
    public void deleteAll() {
        projectRepository.deleteAll();
    }

    @Override
    public void save(ProjectDto projectDto) {
        projectRepository.save(projectMapper.convert(projectDto));
    }

    @Override
    public List<ProjectDto> getAllByNameLike(String text) {
        List<ProjectEntity> entityList = projectRepository.getAllByProjectNameLike(text);
        List<ProjectDto> dtoList = new ArrayList<>();
        for (ProjectEntity entity : entityList) {
            dtoList.add(projectMapper.convert(entity));
        }
        return dtoList;
    }


}
