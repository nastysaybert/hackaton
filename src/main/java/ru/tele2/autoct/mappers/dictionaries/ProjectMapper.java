package ru.tele2.autoct.mappers.dictionaries;

import org.mapstruct.Mapper;
import ru.tele2.autoct.dto.dictionaries.ProjectDto;
import ru.tele2.autoct.jpa.entity.dictionaries.ProjectEntity;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectDto convert (ProjectEntity project);
    ProjectEntity convert (ProjectDto project);
}
