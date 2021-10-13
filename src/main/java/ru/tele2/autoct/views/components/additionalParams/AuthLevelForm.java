package ru.tele2.autoct.views.components.additionalParams;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import ru.tele2.autoct.dto.additionalParams.AuthLevelDto;
import ru.tele2.autoct.services.AuthLevelService;

public class AuthLevelForm extends FormLayout {

    public AuthLevelForm(AuthLevelService authLevelService){
        ComboBox<AuthLevelDto> authLevelSelection = new ComboBox<>();
        authLevelSelection.setWidthFull();
        authLevelSelection.setClearButtonVisible(true);
        authLevelSelection.setItemLabelGenerator(AuthLevelDto::toString);
        authLevelSelection.setItems(authLevelService.getAll());
        this.addFormItem(authLevelSelection,"Выберите уровень полномочий");
    }
}
