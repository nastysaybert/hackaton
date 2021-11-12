package ru.tele2.autoct.views.components.additionalParams;

import com.vaadin.flow.component.combobox.ComboBox;
import ru.tele2.autoct.dto.AdditionalParamDto;
import ru.tele2.autoct.dto.additionalParams.AuthLevelDto;
import ru.tele2.autoct.mappers.additionalParams.AdditionalParamsMapper;
import ru.tele2.autoct.services.additionalParams.AuthLevelService;

public class AuthLevelForm extends ComboBox<AuthLevelDto> {

    public AuthLevelForm(AuthLevelService authLevelService){
        this.setWidthFull();
        this.setClearButtonVisible(true);
//        this.setLabel("Выберите уровень полномочий");
        this.setPlaceholder("Выберите уровень полномочий");
        this.setItemLabelGenerator(AuthLevelDto::toString);
        this.setItems(authLevelService.getAll());
    }

    public AdditionalParamDto getParam(){
        AdditionalParamDto result = new AdditionalParamDto();
        if (this.getValue() != null){
            result.setParamId(this.getValue().getAuthLevelId());
            result.setParamValue(this.getValue().getAuthLevelName());
        } else return null;
        return result;
    }

    public void setParam(AdditionalParamDto additionalParamDto, AuthLevelService authLevelService){
        if (additionalParamDto.getParamId() != null){
            this.setValue(authLevelService.getById(additionalParamDto.getParamId()));
        }
    }
}
