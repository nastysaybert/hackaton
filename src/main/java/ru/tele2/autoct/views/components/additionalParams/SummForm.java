package ru.tele2.autoct.views.components.additionalParams;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.IntegerField;

public class SummForm extends FormLayout {
    public void SummForm(){
        IntegerField amount = new IntegerField();
        amount.setWidthFull();
        this.addFormItem(amount, "Введите сумму");
    }
}
