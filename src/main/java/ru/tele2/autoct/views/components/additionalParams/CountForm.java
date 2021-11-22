package ru.tele2.autoct.views.components.additionalParams;

import com.vaadin.flow.component.textfield.IntegerField;
import ru.tele2.autoct.dto.AdditionalParamDto;

public class CountForm extends IntegerField {
    public CountForm(){
        this.setWidthFull();
        this.setPlaceholder("Введите количество");
//        this.setClearButtonVisible(true);
        this.setHasControls(true);
        this.setMin(1);
    }

    public AdditionalParamDto getParam(){
        AdditionalParamDto result = new AdditionalParamDto();
        if (this.getValue() != null){
            result.setParamId(this.getValue().toString());
            result.setParamValue("шт.");
        } else return null;
        return result;
    }

    public void setParam(AdditionalParamDto additionalParamDto){
        if (additionalParamDto.getParamId() != null){
            this.setValue(Integer.parseInt(additionalParamDto.getParamId()));
        }
    }
}