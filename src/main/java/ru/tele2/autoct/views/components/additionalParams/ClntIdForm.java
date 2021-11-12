package ru.tele2.autoct.views.components.additionalParams;

import com.vaadin.flow.component.textfield.IntegerField;
import ru.tele2.autoct.dto.AdditionalParamDto;

public class ClntIdForm extends IntegerField {

    public ClntIdForm(){
        this.setWidthFull();
//        this.setLabel("Введите продолжительность");
        this.setPlaceholder("Введите CLNT_ID");
        this.setClearButtonVisible(true);
    }

    public AdditionalParamDto getParam(){
        AdditionalParamDto result = new AdditionalParamDto();
        if (this.getValue() != null){
            result.setParamId(this.getValue().toString());
            result.setParamValue("CLNT_ID");
        } else return null;
        return result;
    }

    public void setParam(AdditionalParamDto additionalParamDto){
        if (additionalParamDto.getParamId() != null){
            this.setValue(Integer.parseInt(additionalParamDto.getParamId()));
        }
    }
}
