package ru.tele2.autoct.views.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import ru.tele2.autoct.dto.AdditionalParamDto;
import ru.tele2.autoct.dto.BTEActionDto;
import ru.tele2.autoct.dto.CheckActionDto;
import ru.tele2.autoct.dto.dictionaries.AbonDictionaryDto;
import ru.tele2.autoct.dto.dictionaries.CheckDictionaryDto;
import ru.tele2.autoct.services.additionalParams.*;
import ru.tele2.autoct.services.dictionaries.CheckDictionaryService;
import ru.tele2.autoct.views.components.additionalParams.AdditionalParam;
import ru.tele2.autoct.views.components.serviceViews.CommentField;

import java.util.List;

public class CheckActionForm extends VerticalLayout {

    private ComboBox<CheckDictionaryDto> checkDictionary = new ComboBox<>();
    private HorizontalLayout requiredLine = new HorizontalLayout();
    private HorizontalLayout requiredLineWithParam = new HorizontalLayout();
    private HorizontalLayout optionalLine = new HorizontalLayout();
    private CommentField commentField = new CommentField();
    private Div param = new Div();
    private Button addCommentButton = new Button(new Icon(VaadinIcon.COMMENT_ELLIPSIS_O));
    private AdditionalParam additionalParam;

    public CheckActionForm(CheckActionDto checkActionDto,
                           AbonDictionaryDto abonDictionaryDto,
                           CheckDictionaryService checkDictionaryService,
                           AuthLevelService authLevelService,
                           BranchService branchService,
                           NotifService notifService,
                           ServService servService,
                           TrplService trplService){

        frontFormat(this);
//        this.getStyle().set("padding-left", "5%");
        frontFormat(requiredLine);
        frontFormat(requiredLineWithParam);
        frontFormat(optionalLine);
        optionalLine.setWidth("59%");
        param.setWidth("38.55%");
        requiredLine.setSpacing(false);
        requiredLine.setWidth("61.45%");
        List<CheckDictionaryDto> checkDictList = checkDictionaryService.getAllByAbonDict(abonDictionaryDto);
        checkDictionary.setLabel("Выберите действие проверки");
        checkDictionary.setWidthFull();
        checkDictionary.setClearButtonVisible(true);
        checkDictionary.setItemLabelGenerator(CheckDictionaryDto::getCheckDictName);
        checkDictionary.setItems(checkDictList);
        checkDictionary.focus();

        addCommentButton.getStyle().set("margin-top", "36.6px");
        addCommentButton.getElement().setProperty("title", "Добавить комментарий");
        addCommentButton.addClickListener( event -> {
            if (optionalLine.getComponentCount() == 0){
                constructCommentField();
            } else {
                destructCommentField();
            }
        });

        checkDictionary.addValueChangeListener(element ->{
            param.removeAll();
            if (element.getValue() != null) {
                additionalParam = new AdditionalParam(element.getValue().getBteDictionary().getParamType(),
                                authLevelService, branchService, notifService, servService, trplService);
                param.add(additionalParam);
            }
        });

        if (checkActionDto!=null){
            checkDictionary.setValue(checkActionDto.getCheckDict());
            if (checkActionDto.getComment()!=null ){
                constructCommentField();
                commentField.setValue(checkActionDto.getComment());
            }
            if (checkActionDto.getBteAction()!=null){
                AdditionalParamDto paramDto = new AdditionalParamDto();
                paramDto.setParamId(checkActionDto.getBteAction().getParamId());
                paramDto.setParamValue(checkActionDto.getBteAction().getParamValue());
                additionalParam.setAdditionalParam(paramDto,
                        checkActionDto.getCheckDict().getBteDictionary().getParamType(),
                        authLevelService, branchService, notifService, servService, trplService);
            }
        }

        requiredLine.add(checkDictionary,addCommentButton);
        requiredLineWithParam.add(requiredLine, param);
        this.add(requiredLineWithParam,optionalLine);
    }

    public CheckActionDto getCheckActionDto(){
        CheckActionDto result = new CheckActionDto();
        result.setCheckDict(getCheckDictBox().getValue());
        if (result.getCheckDict().getBteDictionary() != null){
            BTEActionDto bteActionDto = new BTEActionDto();
            bteActionDto.setName(result.getCheckDict().getBteDictionary().getParamType().toString());
            if (additionalParam.getAdditionalParamDto()!=null){
                bteActionDto.setParamId(additionalParam.getAdditionalParamDto().getParamId());
                bteActionDto.setParamValue(additionalParam.getAdditionalParamDto().getParamValue());
            }
            result.setBteAction(bteActionDto);
        }
        if (optionalLine.getComponentCount() != 0){
            result.setComment(commentField.getValue());
        }
        return result;
    }

    public void constructCommentField(){
        commentField = new CommentField();
        optionalLine.add(commentField);
        addCommentButton.setIcon(new Icon(VaadinIcon.COMMENT_O));
        addCommentButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        addCommentButton.getElement().setProperty("title", "Удалить комментарий");
    }

    public void destructCommentField(){
        addCommentButton.setIcon(new Icon(VaadinIcon.COMMENT_ELLIPSIS_O));
        addCommentButton.removeThemeVariants(ButtonVariant.LUMO_ERROR);
        addCommentButton.getElement().setProperty("title", "Добавить комментарий");
        optionalLine.removeAll();
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
        constructCommentField();
        commentField.setValue(comment);
    }

    public String getComment(){
        return this.commentField.getValue();
    }

    public boolean isValid(){
        if (this.checkDictionary.isEmpty()){
            Notification.show("Заполните Действие проверки")
                    .addThemeVariants(NotificationVariant.LUMO_ERROR);
            return false;
        } else
            if ((this.param.getChildren().count() != 0) && (!additionalParam.isValid())){
                return false;
            }
        return true;
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
