package ru.tele2.autoct.views.components;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;

public class InitialDataForm extends HorizontalLayout {
    public InitialDataForm(){

        this.setMargin(false);
        this.setPadding(false);
        this.setWidth("70%");

        TextArea action = new TextArea();
        action.setLabel("Действие абонента");
        action.setWidthFull();
        action.getStyle().set("padding-top", "0px");
        action.getStyle().set("padding-bottom", "0px");

        TextArea result = new TextArea();
        result.setWidthFull();
        result.setLabel("Проверка результата");
        result.getStyle().set("padding-top", "0px");
        result.getStyle().set("padding-bottom", "0px");

        this.add(action, result);
    }
}
