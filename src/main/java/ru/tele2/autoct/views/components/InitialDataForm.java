package ru.tele2.autoct.views.components;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import ru.tele2.autoct.dto.InitialDataDto;

public class InitialDataForm extends HorizontalLayout {

    private TextArea action;
    private TextArea check;

    public InitialDataForm(){

        this.setMargin(false);
        this.setPadding(false);
        this.setWidth("85%");
        this.getStyle().set("margin-bottom", "5px");
        this.setId("InitialDataForm");

        action = new TextArea();
        action.setPlaceholder("Действие абонента");
        action.setWidthFull();
        action.getStyle().set("padding-top", "0px");
        action.getStyle().set("padding-bottom", "0px");

        check = new TextArea();
        check.setWidthFull();
        check.setPlaceholder("Проверка результата");
        check.getStyle().set("padding-top", "0px");
        check.getStyle().set("padding-bottom", "0px");

        this.add(action, check);
    }

    public InitialDataDto getInitialDataDto(){
        InitialDataDto result = new InitialDataDto();
        result.setInitialAction(action.getValue());
        result.setInitialCheck(check.getValue());
        return result;
    }

    public boolean isValid(){
        if (this.action.isEmpty() || this.check.isEmpty()){
            return false;
        } else return true;
    }

    public void setValue(InitialDataDto initialDataDto){
        this.action.setValue(initialDataDto.getInitialAction());
        this.check.setValue(initialDataDto.getInitialCheck());
    }
}
