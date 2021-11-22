package ru.tele2.autoct.views.components.additionalParams;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import ru.tele2.autoct.dto.AdditionalParamDto;

public class TextForm extends TextField {
    public TextForm(){
        this.setWidthFull();
        this.setPlaceholder("Введите текст");
        this.setClearButtonVisible(true);
        this.setMaxLength(160);
        this.setSuffixComponent(new Div(new Text("0/"+160)));
        this.addValueChangeListener(e -> {
            if (e.getValue().length()>this.getMaxLength()){
                this.getStyle().set("border-color", "2px solid red");
            } else this.getStyle().set("border-color", "");
            this.setSuffixComponent(new Div(new Text(e.getValue().length() + "/160")));
//                    + this.getMaxLength()));
        });
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
