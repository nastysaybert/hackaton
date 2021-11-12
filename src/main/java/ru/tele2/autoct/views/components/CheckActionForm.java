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
import ru.tele2.autoct.dto.AdditionalParamDto;
import ru.tele2.autoct.dto.BTEActionDto;
import ru.tele2.autoct.dto.CheckActionDto;
import ru.tele2.autoct.dto.dictionaries.AbonDictionaryDto;
import ru.tele2.autoct.dto.dictionaries.CheckDictionaryDto;
import ru.tele2.autoct.enums.ParamType;
import ru.tele2.autoct.services.additionalParams.*;
import ru.tele2.autoct.views.components.additionalParams.AdditionalParam;
import ru.tele2.autoct.views.components.serviceViews.CommentField;

import java.util.ArrayList;
import java.util.List;

public class CheckActionForm extends VerticalLayout {

    private ComboBox<CheckDictionaryDto> checkDictionary = new ComboBox<>();
    private HorizontalLayout requiredLine = new HorizontalLayout();
    private HorizontalLayout requiredLineWithParam = new HorizontalLayout();
    private HorizontalLayout optionalLine = new HorizontalLayout();
    private CommentField commentField = new CommentField();
    private VerticalLayout paramsLayout = new VerticalLayout();
    private Button addCommentButton = new Button(new Icon(VaadinIcon.COMMENT_ELLIPSIS_O));
    private List<AdditionalParam> additionalParams = new ArrayList<>();

    public CheckActionForm(CheckActionDto checkActionDto,
                           AbonDictionaryDto abonDictionaryDto,
                           Registrator registrator){

        frontFormat(this);
//        this.getStyle().set("padding-left", "5%");
        this.getStyle().set("border-radius", "10px 10px 10px 10px");
        this.getStyle().set("border", "2px solid var(--lumo-primary-color-10pct)");
        this.getStyle().set("padding-left", "10px");
        this.getStyle().set("padding-right", "10px");
//        this.getStyle().set("padding-bottom", "10px");
        this.getStyle().set("margin-bottom", "5px");
        frontFormat(requiredLine);
        frontFormat(requiredLineWithParam);
        frontFormat(optionalLine);
        frontFormat(paramsLayout);
        paramsLayout.setSpacing(false);
        optionalLine.setWidth("59%");
        optionalLine.getStyle().set("margin-top", "0px");
        paramsLayout.setWidth("35%");
        requiredLine.setSpacing(false);
        requiredLine.setWidth("65%");
        List<CheckDictionaryDto> checkDictList = registrator.getCheckDictionaryService().getAllByAbonDict(abonDictionaryDto);
//        checkDictionary.setLabel("Выберите действие проверки");
        checkDictionary.setPlaceholder("Выберите действие проверки");
        checkDictionary.setWidthFull();
        checkDictionary.setClearButtonVisible(true);
        checkDictionary.setItemLabelGenerator(CheckDictionaryDto::getCheckDictName);
        checkDictionary.setItems(checkDictList);
        checkDictionary.focus();

//        addCommentButton.getStyle().set("margin-top", "36.6px");
        addCommentButton.getElement().setProperty("title", "Добавить комментарий");
        addCommentButton.addClickListener( event -> {
            if (optionalLine.getComponentCount() == 0){
                constructCommentField();
            } else {
                destructCommentField();
            }
        });

        checkDictionary.addValueChangeListener(element ->{
            paramsLayout.removeAll();
            if (element.getValue() != null) {
                if (element.getValue().getBteDictionary() != null){
                    List<ParamType> paramList =
                            registrator.getBteDictionaryService().parseParamTypes(element.getValue().getBteDictionary());
                    paramList.forEach(paramType ->{
                        AdditionalParam param = new AdditionalParam(paramType,registrator);
                        additionalParams.add(param);
                        paramsLayout.add(param);
                    });
                }
            }
        });

        if (checkActionDto!=null){
            checkDictionary.setValue(checkActionDto.getCheckDict());
            if (checkActionDto.getComment()!=null ){
                constructCommentField();
                commentField.setValue(checkActionDto.getComment());
            }
            if (checkActionDto.getBteActions()!= null){
                for (int i = 0; i<checkActionDto.getBteActions().size(); i++ ) {
                    AdditionalParamDto paramDto = new AdditionalParamDto();
                    paramDto.setParamId(checkActionDto.getBteActions().get(i).getParamId());
                    paramDto.setParamValue(checkActionDto.getBteActions().get(i).getParamValue());
                    additionalParams.get(i).setAdditionalParam(paramDto,
                            checkActionDto.getBteActions().get(i).getParamType(),registrator);
                }
            }
        }

        requiredLine.add(checkDictionary,addCommentButton);
        requiredLineWithParam.add(requiredLine, paramsLayout);
        this.add(requiredLineWithParam,optionalLine);
    }

    public CheckActionDto getCheckActionDto(){
        CheckActionDto result = new CheckActionDto();
        result.setCheckDict(getCheckDictBox().getValue());
        if (result.getCheckDict().getBteDictionary() != null){
            List<BTEActionDto> bteActions = new ArrayList<>();
            if (additionalParams.size()>0){
                additionalParams.forEach(additionalParam -> {
                    if (additionalParam.getAdditionalParamDto()!=null){
                        BTEActionDto bteActionDto = new BTEActionDto();
                        bteActionDto.setParamType(additionalParam.getCurrentParamType());
                        bteActionDto.setParamId(additionalParam.getAdditionalParamDto().getParamId());
                        bteActionDto.setParamValue(additionalParam.getAdditionalParamDto().getParamValue());
                        bteActions.add(bteActionDto);
                    }
                });
            }
            result.setBteActions(bteActions);
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

    public ComboBox<CheckDictionaryDto> getCheckDictBox() {
        return checkDictionary;
    }

    public boolean isValid(){
        if (this.checkDictionary.isEmpty()){
            Notification.show("Заполните Действие проверки")
                    .addThemeVariants(NotificationVariant.LUMO_ERROR);
            return false;
        } else
        if (this.paramsLayout.getChildren().count() != 0){
            for (AdditionalParam additionalParam : additionalParams) {
                if (!additionalParam.isValid()){
                    return false;
                }
            }
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
