package ru.tele2.autoct.views.components.additionalParams;

import com.vaadin.flow.component.combobox.ComboBox;
import ru.tele2.autoct.dto.additionalParams.AuthLevelDto;
import ru.tele2.autoct.services.additionalParams.AuthLevelService;

public class AuthLevelForm extends ComboBox<AuthLevelDto> {

    public AuthLevelForm(AuthLevelService authLevelService){
        this.setWidthFull();
        this.setClearButtonVisible(true);
        this.setLabel("Выберите уровень полномочий");
        this.setItemLabelGenerator(AuthLevelDto::toString);
        this.setItems(authLevelService.getAll());
    }
}
