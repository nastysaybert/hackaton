package ru.tele2.autoct.views.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import ru.tele2.autoct.dto.dictionaries.AbonDictionaryDto;
import ru.tele2.autoct.services.dictionaries.AbonDictionaryService;
import java.util.List;

public class AbonActionForm extends VerticalLayout {
    private ComboBox<AbonDictionaryDto> abonDictionary = new ComboBox<>();
    private HorizontalLayout requiredLine = new HorizontalLayout();
    private HorizontalLayout optionalLine = new HorizontalLayout();
    private CommentField commentField = new CommentField();
    private Button addCommentButton = new Button(new Icon(VaadinIcon.COMMENT_ELLIPSIS_O));

    public AbonActionForm (AbonDictionaryService abonDictionaryService){
        frontFormat(this);
        frontFormat(requiredLine);
        frontFormat(optionalLine);
        requiredLine.setSpacing(false);

        List<AbonDictionaryDto> abonDictList = abonDictionaryService.getAll();
        abonDictionary.setLabel("Выберите действие абонента");
        abonDictionary.setClearButtonVisible(true);
        abonDictionary.setItemLabelGenerator(AbonDictionaryDto::getAbonDictName);
        abonDictionary.setItems(abonDictList);
        abonDictionary.setId("AbonActionForm");
        abonDictionary.setWidth("95%");

        addCommentButton.getStyle().set("margin-top", "36.6px");
        addCommentButton.addClickListener( event -> {
            if (optionalLine.getComponentCount() == 0){
                commentField = new CommentField();
                optionalLine.add(commentField);
                addCommentButton.setIcon(new Icon(VaadinIcon.COMMENT_O));
                addCommentButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
            } else {
                addCommentButton.setIcon(new Icon(VaadinIcon.COMMENT_ELLIPSIS_O));
                addCommentButton.removeThemeVariants(ButtonVariant.LUMO_ERROR);
                optionalLine.removeAll();
            }

        });
        requiredLine.add(abonDictionary,addCommentButton);
        this.add(requiredLine,optionalLine);
    }

    public AbonDictionaryDto getAbonDictionaryDto(){
        return this.abonDictionary.getValue();
    }

    public void setAbonDictionaryDto(AbonDictionaryDto abonDictionaryDto){
        this.abonDictionary.setValue(abonDictionaryDto);
    }

    public ComboBox<AbonDictionaryDto> getAbonDictBox() {
        return abonDictionary;
    }

    public String getComment(){
        return this.commentField.getValue();
    }

    public void setComment(String comment){
        this.commentField = new CommentField();
        commentField.setValue(comment);
        optionalLine.add(commentField);
        addCommentButton.setIcon(new Icon(VaadinIcon.COMMENT_O));
        addCommentButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
    }

    public boolean isValid(){
        if (this.abonDictionary.isEmpty()){
            Notification.show("Заполните Действие абонента")
                    .addThemeVariants(NotificationVariant.LUMO_ERROR);
            return false;
        } else return true;
    }

    private void frontFormat (HorizontalLayout component){
        component.setPadding(false);
        component.setMargin(false);
        component.setWidthFull();
    }

    private void frontFormat (VerticalLayout component){
        component.setPadding(false);
        component.setMargin(false);
        component.setWidthFull();
    }
}
