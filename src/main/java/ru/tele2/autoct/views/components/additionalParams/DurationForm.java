package ru.tele2.autoct.views.components.additionalParams;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.IntegerField;

public class DurationForm extends FormLayout {
    public void DurationForm(){
        IntegerField duration = new IntegerField();
        duration.setWidthFull();
        this.addFormItem(duration, "Введите продолжительность");
    }
}
