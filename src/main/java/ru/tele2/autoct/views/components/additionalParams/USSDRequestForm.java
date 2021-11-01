package ru.tele2.autoct.views.components.additionalParams;

import com.vaadin.flow.component.textfield.TextField;
import ru.tele2.autoct.dto.AdditionalParamDto;

public class USSDRequestForm extends TextField {
    public USSDRequestForm(){
        this.setWidthFull();
        this.setLabel("Введите USSD-команду");
        this.setClearButtonVisible(true);
    }

    public AdditionalParamDto getParam(){
        AdditionalParamDto result = new AdditionalParamDto();
        if (this.getValue() != null){
            result.setParamId(this.getValue());
            result.setParamValue("");
        } else return null;
        return result;
    }

    public void setParam(AdditionalParamDto additionalParamDto){
        if (additionalParamDto.getParamId() != null){
            this.setValue(additionalParamDto.getParamId());
        }
    }
}