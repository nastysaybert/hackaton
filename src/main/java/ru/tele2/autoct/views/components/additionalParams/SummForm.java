package ru.tele2.autoct.views.components.additionalParams;

import com.vaadin.flow.component.textfield.IntegerField;
import ru.tele2.autoct.dto.AdditionalParamDto;

public class SummForm extends IntegerField {
    public SummForm(){
        this.setWidthFull();
        this.setLabel("Введите сумму");
        this.setClearButtonVisible(true);
    }

    public AdditionalParamDto getParam(){
        AdditionalParamDto result = new AdditionalParamDto();
        if (this.getValue() != null){
            result.setParamId(this.getValue().toString());
            result.setParamValue("руб.");
        } else return null;
        return result;
    }
}
