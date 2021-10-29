package ru.tele2.autoct.views.components;

import com.vaadin.flow.component.PushConfiguration;
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
import com.vaadin.flow.internal.Pair;
import ru.tele2.autoct.dto.AbonActionDto;
import ru.tele2.autoct.dto.AdditionalParamDto;
import ru.tele2.autoct.dto.BTEActionDto;
import ru.tele2.autoct.dto.dictionaries.AbonDictionaryDto;
import ru.tele2.autoct.services.additionalParams.*;
import ru.tele2.autoct.services.dictionaries.AbonDictionaryService;
import ru.tele2.autoct.services.dictionaries.CheckDictionaryService;
import ru.tele2.autoct.views.components.additionalParams.AdditionalParam;

import java.util.List;

public class AbonActionForm extends VerticalLayout {

    private ComboBox<AbonDictionaryDto> abonDictionary = new ComboBox<>();
    private HorizontalLayout requiredLine = new HorizontalLayout();
    private HorizontalLayout requiredLineWithParam = new HorizontalLayout();
    private HorizontalLayout optionalLine = new HorizontalLayout();
    private CommentField commentField = new CommentField();
    private Button addCommentButton = new Button(new Icon(VaadinIcon.COMMENT_ELLIPSIS_O));
    private Div param = new Div();
    private AdditionalParam additionalParam;

    public AbonActionForm (AbonActionDto abonActionDto,
                           VerticalLayout checkActionsLayout,
                           AbonDictionaryService abonDictionaryService,
                           //CheckDictionaryService checkDictionaryService,
                           AuthLevelService authLevelService,
                           BranchService branchService,
                           NotifService notifService,
                           ServService servService,
                           TrplService trplService){
        frontFormat(this);
        frontFormat(requiredLine);
        frontFormat(requiredLineWithParam);
        frontFormat(optionalLine);
        requiredLine.setSpacing(false);
        requiredLine.setWidth("60%");
        optionalLine.setWidth("58%");
        param.setWidth("40%");

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
                constructCommentField();
            } else {
                addCommentButton.setIcon(new Icon(VaadinIcon.COMMENT_ELLIPSIS_O));
                addCommentButton.removeThemeVariants(ButtonVariant.LUMO_ERROR);
                optionalLine.removeAll();
            }
        });

        abonDictionary.addValueChangeListener(element ->{
            param.removeAll();
            checkActionsLayout.removeAll();
            if ((element.getValue() != null) ) {
                if (element.getValue().getBteDictionary() != null){
                    additionalParam = new AdditionalParam(element.getValue().getBteDictionary().getParamType(),
                            authLevelService, branchService, notifService, servService, trplService);
                    param.add(additionalParam);
                }
            }
        });

        if (abonActionDto!=null){
            abonDictionary.setValue(abonActionDto.getAbonDict());
            if (abonActionDto.getComment()!=null ){
                constructCommentField();
                commentField.setValue(abonActionDto.getComment());
            }
            if (abonActionDto.getBteAction()!=null){
                AdditionalParamDto paramDto = new AdditionalParamDto();
                paramDto.setParamId(abonActionDto.getBteAction().getParamId());
                paramDto.setParamValue(abonActionDto.getBteAction().getParamValue());
                additionalParam.setAdditionalParam(paramDto,
                        abonActionDto.getAbonDict().getBteDictionary().getParamType(),
                        authLevelService, branchService, notifService, servService, trplService);
            }
        }

        requiredLine.add(abonDictionary,addCommentButton);
        requiredLineWithParam.add(requiredLine, param);
        this.add(requiredLineWithParam,optionalLine,checkActionsLayout);
    }

    public AbonActionDto getAbonActionDto(){
        AbonActionDto result = new AbonActionDto();
        result.setAbonDict(getAbonDictBox().getValue());
        if (result.getAbonDict().getBteDictionary() != null){
            BTEActionDto bteActionDto = new BTEActionDto();
            bteActionDto.setName(result.getAbonDict().getBteDictionary().getParamType().toString());
            bteActionDto.setParamId(additionalParam.getAdditionalParamDto().getParamId());
            bteActionDto.setParamValue(additionalParam.getAdditionalParamDto().getParamValue());
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
        constructCommentField();
        commentField.setValue(comment);
    }

    public boolean isValid(){
        if (this.abonDictionary.isEmpty()){
            Notification.show("Заполните Действие абонента")
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
