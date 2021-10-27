package ru.tele2.autoct.views.components.additionalParams;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import ru.tele2.autoct.dto.AdditionalParamDto;

public class SummForm extends NumberField {
    public SummForm(){
//        IntegerField
//        this.setWidthFull();
//        this.setLabel("Введите сумму");
//        this.setClearButtonVisible(true);
        this.setWidthFull();
        this.setLabel("Введите сумму");
        this.setClearButtonVisible(true);
        Div euroSuffix = new Div();
        euroSuffix.setText("₱");
        this.setSuffixComponent(euroSuffix);
    }

    public AdditionalParamDto getParam(){
        AdditionalParamDto result = new AdditionalParamDto();
        if (this.getValue() != null){
            result.setParamId(this.getValue().toString());
            result.setParamValue("руб.");
        } else return null;
        return result;
    }

    public void setParam(AdditionalParamDto additionalParamDto){
        this.setValue(Double.parseDouble(additionalParamDto.getParamId()));
    }
}
