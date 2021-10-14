package ru.tele2.autoct.views.components.additionalParams;

import com.vaadin.flow.component.textfield.TextField;

public class USSDRequestForm extends TextField {
    public USSDRequestForm(){
        this.setWidthFull();
//        this.setWidth("25%");
        this.setLabel("Введите USSD-команду");
        this.setClearButtonVisible(true);
    }
}