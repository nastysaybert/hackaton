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
import ru.tele2.autoct.dto.AbonActionDto;
import ru.tele2.autoct.dto.AdditionalParamDto;
import ru.tele2.autoct.dto.BTEActionDto;
import ru.tele2.autoct.dto.dictionaries.AbonDictionaryDto;
import ru.tele2.autoct.enums.ParamType;
import ru.tele2.autoct.services.additionalParams.*;
import ru.tele2.autoct.views.components.additionalParams.AdditionalParam;
import ru.tele2.autoct.views.components.serviceViews.CommentField;

import java.util.ArrayList;
import java.util.List;

public class AbonActionForm extends VerticalLayout {

    private ComboBox<AbonDictionaryDto> abonDictionary = new ComboBox<>();
    private HorizontalLayout requiredLine = new HorizontalLayout();
    private HorizontalLayout requiredLineWithParam = new HorizontalLayout();
    private HorizontalLayout optionalLine = new HorizontalLayout();
    private CommentField commentField = new CommentField();
    private Button addCommentButton = new Button(new Icon(VaadinIcon.COMMENT_ELLIPSIS_O));
    private VerticalLayout paramsLayout = new VerticalLayout();
    private List<AdditionalParam> additionalParams = new ArrayList<>();

    public AbonActionForm (AbonActionDto abonActionDto,
                           VerticalLayout checkActionsLayout,
                           Registrator registrator){
        frontFormat(this);
        frontFormat(requiredLine);
        frontFormat(requiredLineWithParam);
        frontFormat(optionalLine);
        frontFormat(paramsLayout);
        optionalLine.getStyle().set("margin-top", "0px");
        paramsLayout.setSpacing(false);
        requiredLine.setSpacing(false);
        requiredLine.setWidth("60%");
        optionalLine.setWidth("58%");
        paramsLayout.setWidth("40%");

        List<AbonDictionaryDto> abonDictList = registrator.getAbonDictionaryService().getAll();
//        abonDictionary.setLabel("Выберите действие абонента");
        abonDictionary.setPlaceholder("Выберите действие абонента");
        abonDictionary.setClearButtonVisible(true);
        abonDictionary.setItemLabelGenerator(AbonDictionaryDto::getAbonDictName);
        abonDictionary.setItems(abonDictList);
        abonDictionary.setWidth("95%");
        abonDictionary.focus();


//        addCommentButton.getStyle().set("margin-top", "36.6px");
        addCommentButton.getElement().setProperty("title", "Добавить комментарий");
        addCommentButton.addClickListener( event -> {
            if (optionalLine.getComponentCount() == 0){
                constructCommentField();
            } else {
                destructCommentField();
            }
        });

        abonDictionary.addValueChangeListener(element ->{
            paramsLayout.removeAll();
            //очищаем (читай = пересоздаем) список параметров
            additionalParams = new ArrayList<>();
            checkActionsLayout.removeAll();
            if ((element.getValue() != null) ) {
                if (element.getValue().getBteDictionary() != null){
                    List<ParamType> paramList =
                            registrator.getBteDictionaryService().parseParamTypes(element.getValue().getBteDictionary());
                    paramList.forEach(paramType ->{
                        AdditionalParam param = new AdditionalParam(paramType, registrator);
                        additionalParams.add(param);
                        paramsLayout.add(param);
                    });
                }
            }
        });

        if (abonActionDto!=null){
            abonDictionary.setValue(abonActionDto.getAbonDict());
            if (abonActionDto.getComment()!=null ){
                constructCommentField();
                commentField.setValue(abonActionDto.getComment());
            }

            if (abonActionDto.getBteActions()!= null){
                for (int i = 0; i<abonActionDto.getBteActions().size(); i++ ) {
                    AdditionalParamDto paramDto = new AdditionalParamDto();
                    paramDto.setParamId(abonActionDto.getBteActions().get(i).getParamId());
                    paramDto.setParamValue(abonActionDto.getBteActions().get(i).getParamValue());
                    additionalParams.get(i).setAdditionalParam(paramDto,
                            abonActionDto.getBteActions().get(i).getParamType(), registrator);
                }
            }
        }

        requiredLine.add(abonDictionary,addCommentButton);
        requiredLineWithParam.add(requiredLine, paramsLayout);
        this.add(requiredLineWithParam,optionalLine,checkActionsLayout);
    }

    public AbonActionDto getAbonActionDto(){
        AbonActionDto result = new AbonActionDto();
        result.setAbonDict(getAbonDictBox().getValue());
        if (result.getAbonDict() != null){
            if (result.getAbonDict().getBteDictionary() != null){
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

    public AbonDictionaryDto getAbonDictionaryDto(){
        return this.abonDictionary.getValue();
    }

    public ComboBox<AbonDictionaryDto> getAbonDictBox() {
        return abonDictionary;
    }

    public boolean isValid(){
        if (this.abonDictionary.isEmpty()){
            Notification.show("Заполните Действие абонента")
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
