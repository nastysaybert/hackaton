package ru.tele2.autoct.views.components;


import com.vaadin.flow.component.textfield.TextArea;

public class CommentField extends TextArea {
    public CommentField(){
        this.setWidthFull();
        this.setPlaceholder("Введите комментарий");
        this.setClearButtonVisible(true);
        this.setHeight("57px");
        this.setWidth("92.5%");
        this.getStyle().set("padding-top", "0px")
                .set("margin-top", "0px");
    }
}
