package ru.tele2.autoct.views.components.serviceViews;

import com.vaadin.flow.component.combobox.ComboBox;
import ru.tele2.autoct.dto.dictionaries.ProjectDto;
import ru.tele2.autoct.services.dictionaries.ProjectService;

public class ProjectForm extends ComboBox<ProjectDto> {

    public ProjectForm(ProjectService projectService){
        this.setWidth("45%");
        this.setClearButtonVisible(true);
        this.setPlaceholder("Выберите проект");
        this.setItemLabelGenerator(ProjectDto::toString);
        this.setItems(projectService.getAll());
        this.getElement().getStyle().set("margin-left", "10%");
    }

    public ProjectDto getProject(){
        if (this.getValue() != null){
            return this.getValue();
        } else return null;
    }

    public void setProject(ProjectDto project){
        if (project != null){
            this.setValue(project);
        }
    }
}
