package ru.tele2.autoct.views.components.additionalParams;

import com.vaadin.flow.component.textfield.IntegerField;

public class DurationForm extends IntegerField {
    public DurationForm(){
        this.setWidthFull();
//        this.setWidth("25%");
        this.setLabel("Введите продолжительность");
        this.setClearButtonVisible(true);
    }
}
