package ru.tele2.autoct.views.components.additionalParams;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;

public class USSDRequestForm extends TextField {
    public USSDRequestForm(){
//        this.setWidthFull();
        this.setLabel("Введите USSD-команду");
        this.setClearButtonVisible(true);
    }
}