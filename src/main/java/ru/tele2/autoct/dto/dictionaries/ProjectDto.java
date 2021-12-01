package ru.tele2.autoct.dto.dictionaries;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
public class ProjectDto {

    private Long projectId;

    private String projectName;

    public String toString(){
        return projectName +
                " (project_id = "
                + projectId
                + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectDto that = (ProjectDto) o;
        return Objects.equals(projectId, that.projectId) &&
                Objects.equals(projectName, that.projectName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, projectName);
    }
}
