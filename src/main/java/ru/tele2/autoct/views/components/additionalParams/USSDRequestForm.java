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
        result.setParamId(this.getValue());
        result.setParamValue("");
        return result;
    }
}