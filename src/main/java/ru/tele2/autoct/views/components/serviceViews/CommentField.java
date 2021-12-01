package ru.tele2.autoct.views.components.serviceViews;


import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextArea;

@CssImport(value = "./styles/comment-field.css", themeFor = "vaadin-text-area")
public class CommentField extends TextArea {
    public CommentField(){
        this.setWidthFull();
        this.setPlaceholder("Введите комментарий");
        this.setClearButtonVisible(true);
        this.setHeight("57px");
        this.setWidth("92%");
        this.getStyle().set("margin-left", "5%");
        this.getStyle().set("padding-top", "0px")
                .set("margin-top", "0px");
    }
}
