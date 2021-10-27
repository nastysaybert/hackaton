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
import ru.tele2.autoct.dto.dictionaries.CheckDictionaryDto;
import ru.tele2.autoct.services.dictionaries.CheckDictionaryService;

import java.util.List;

public class CheckActionForm extends VerticalLayout {

    private ComboBox<CheckDictionaryDto> checkDictionary = new ComboBox<>();
    private HorizontalLayout requiredLine = new HorizontalLayout();
    private HorizontalLayout optionalLine = new HorizontalLayout();
    private CommentField commentField = new CommentField();
    Button addCommentButton = new Button(new Icon(VaadinIcon.COMMENT_ELLIPSIS_O));

    public CheckActionForm(AbonDictionaryDto abonDictionaryDto,
                           CheckDictionaryService checkDictionaryService){

        frontFormat(this);
        frontFormat(requiredLine);
        frontFormat(optionalLine);
        requiredLine.setSpacing(false);
        List<CheckDictionaryDto> checkDictList = checkDictionaryService.getAllByAbonDict(abonDictionaryDto);
        checkDictionary.setLabel("Выберите действие проверки");
        checkDictionary.setWidthFull();
        checkDictionary.setClearButtonVisible(true);
        checkDictionary.setItemLabelGenerator(CheckDictionaryDto::getCheckDictName);
        checkDictionary.setItems(checkDictList);
        checkDictionary.setId("CheckActionForm");

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
        requiredLine.add(checkDictionary,addCommentButton);
        this.add(requiredLine,optionalLine);
    }

    public CheckDictionaryDto getCheckDictionaryDto(){
        return this.checkDictionary.getValue();
    }

    public void setCheckDictionaryDto(CheckDictionaryDto checkDictionaryDto){
        this.checkDictionary.setValue(checkDictionaryDto);
    }

    public ComboBox<CheckDictionaryDto> getCheckDictBox() {
        return checkDictionary;
    }

    public void setComment(String comment){
        this.commentField = new CommentField();
        commentField.setValue(comment);
        optionalLine.add(commentField);
        addCommentButton.setIcon(new Icon(VaadinIcon.COMMENT_O));
        addCommentButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
    }

    public String getComment(){
        return this.commentField.getValue();
    }

    public boolean isValid(){
        if (this.checkDictionary.isEmpty()){
            Notification.show("Заполните Действие проверки")
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
