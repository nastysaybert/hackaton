package ru.tele2.autoct.views.components.additionalParams;

import com.vaadin.flow.component.textfield.IntegerField;
import ru.tele2.autoct.dto.AdditionalParamDto;

public class DurationForm extends IntegerField {
    public DurationForm(){
        this.setWidthFull();
        this.setLabel("Введите продолжительность");
        this.setClearButtonVisible(true);
    }

    public AdditionalParamDto getParam(){
        AdditionalParamDto result = new AdditionalParamDto();
        if (this.getValue() != null){
            result.setParamId(this.getValue().toString());
            result.setParamValue("сек.");
        } else return null;
        return result;
    }
}
