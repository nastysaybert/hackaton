package ru.saybert.hackaton.mappers.dictionaries;

import org.mapstruct.Mapper;
import ru.saybert.hackaton.dto.dictionaries.ProjectDto;
import ru.saybert.hackaton.jpa.entity.dictionaries.ProjectEntity;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectDto convert (ProjectEntity project);
    ProjectEntity convert (ProjectDto project);
}
