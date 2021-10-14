package ru.tele2.autoct.views.components.additionalParams;

import com.vaadin.flow.component.textfield.IntegerField;

public class SummForm extends IntegerField {
    public SummForm(){
        this.setWidthFull();
//        this.setWidth("25%");
        this.setLabel("Введите сумму");
        this.setClearButtonVisible(true);
    }
}
