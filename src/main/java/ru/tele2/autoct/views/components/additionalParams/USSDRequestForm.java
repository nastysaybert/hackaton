package ru.tele2.autoct.views.components.additionalParams;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;

public class USSDRequestForm extends FormLayout {
    public void USSDRequestForm(){
        TextField ussd = new TextField();
        ussd.setWidthFull();
        this.addFormItem(ussd, "Введите USSD-команду");
    }
}
