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
import ru.tele2.autoct.enums.ParamType;
import ru.tele2.autoct.services.additionalParams.*;
import ru.tele2.autoct.services.dictionaries.BTEDictionaryService;
import ru.tele2.autoct.services.dictionaries.CheckDictionaryService;
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
                           CheckDictionaryService checkDictionaryService,
                           BTEDictionaryService bteDictionaryService,
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
        frontFormat(paramsLayout);
        paramsLayout.setSpacing(false);
        optionalLine.setWidth("59%");
        paramsLayout.setWidth("38.55%");
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
            paramsLayout.removeAll();
            if (element.getValue() != null) {
                if (element.getValue().getBteDictionary() != null){
                    List<ParamType> paramList =
                            bteDictionaryService.parseParamTypes(element.getValue().getBteDictionary());
                    paramList.forEach(paramType ->{
                        AdditionalParam param = new AdditionalParam(paramType,
                                authLevelService, branchService, notifService, servService, trplService);
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
            if (checkActionDto.getBteActions().size() > 0){
//                List<ParamType> paramList =
//                        bteDictionaryService.parseParamTypes(abonActionDto.getAbonDict().getBteDictionary());
                checkActionDto.getBteActions().forEach(bteActionDto -> {
                    AdditionalParamDto paramDto = new AdditionalParamDto();
                    paramDto.setParamId(bteActionDto.getParamId());
                    paramDto.setParamValue(bteActionDto.getParamValue());
                    AdditionalParam param = new AdditionalParam(bteActionDto.getParamType(),
                            authLevelService, branchService, notifService, servService, trplService);
                    param.setAdditionalParam(paramDto, bteActionDto.getParamType(),
                            authLevelService, branchService, notifService, servService, trplService);
                    additionalParams.add(param);
                    paramsLayout.add(param);
                });
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
                    BTEActionDto bteActionDto = new BTEActionDto();
                    bteActionDto.setParamType(additionalParam.getCurrentParamType());
                    bteActionDto.setParamId(additionalParam.getAdditionalParamDto().getParamId());
                    bteActionDto.setParamValue(additionalParam.getAdditionalParamDto().getParamValue());
                    bteActions.add(bteActionDto);
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
