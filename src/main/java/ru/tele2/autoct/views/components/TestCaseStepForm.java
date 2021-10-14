package ru.tele2.autoct.views.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import ru.tele2.autoct.dto.dictionaries.AbonDictionaryDto;
import ru.tele2.autoct.dto.dictionaries.CheckDictionaryDto;
import ru.tele2.autoct.services.additionalParams.*;
import ru.tele2.autoct.services.dictionaries.AbonDictionaryService;
import ru.tele2.autoct.services.dictionaries.CheckDictionaryService;
import ru.tele2.autoct.views.components.additionalParams.AdditionalParam;

public class TestCaseStepForm extends VerticalLayout {
    public TestCaseStepForm(AbonDictionaryService abonDictionaryService,
                            CheckDictionaryService checkDictionaryService,
                            AuthLevelService authLevelService,
                            BranchService branchService,
                            NotifService notifService,
                            ServService servService,
                            TrplService trplService){
//        this.add(new Label("Шаг ТК"));
        this.setMargin(false);
        this.setPadding(false);
        this.setWidth("70%");
        this.setSpacing(false);
        HorizontalLayout abonActionLine = new HorizontalLayout();
        VerticalLayout checkActionsLayout = new VerticalLayout();
        HorizontalLayout buttonsLine = new HorizontalLayout();
        Div param = new Div();
        param.setWidth("40%");
        abonActionLine.setMargin(false);
        abonActionLine.setPadding(false);
        abonActionLine.setWidthFull();
        checkActionsLayout.setMargin(false);
        checkActionsLayout.setPadding(false);
        checkActionsLayout.setSpacing(false);
        checkActionsLayout.setWidthFull();
        checkActionsLayout.getStyle().set("padding-left", "5%");
//        checkActionsLayout.getStyle().set("border", "2px solid red");
        buttonsLine.setMargin(false);
        buttonsLine.setPadding(false);
        buttonsLine.setWidthFull();

        Button newCheckActionButton = new Button("Добавить действие проверки");
        newCheckActionButton.setVisible(false);

        ComboBox<AbonDictionaryDto> abonAction = new AbonActionForm(abonDictionaryService);
        abonAction.setWidth("60%");
        abonAction.addValueChangeListener(element ->{
            param.removeAll();
            checkActionsLayout.removeAll();
            if ((element.getValue() != null) ) {
                if (element.getValue().getBteDictionary() != null){
                    param.add(new AdditionalParam(element.getValue().getBteDictionary().getParamType(),
                            authLevelService, branchService, notifService, servService, trplService));
                }
                if (checkDictionaryService.getAllByAbonDict(element.getValue()).size() != 0){
                    newCheckActionButton.setVisible(true);
                }
            } else newCheckActionButton.setVisible(false);
        });
        abonActionLine.add(abonAction, param);

        newCheckActionButton.addClickListener(event -> {
            HorizontalLayout checkActionLine = new HorizontalLayout();
            checkActionLine.setMargin(false);
            checkActionLine.setPadding(false);
            checkActionLine.setWidthFull();
            Div checkParam = new Div();
            checkParam.setWidth("39%");
            ComboBox<CheckDictionaryDto> checkActionForm = new CheckActionForm(abonAction.getValue(), checkDictionaryService);
            checkActionForm.setWidth("61%");
            checkActionForm.addValueChangeListener(element ->{
                checkParam.removeAll();
                if (element.getValue() != null) {
                    checkParam.add(new AdditionalParam(element.getValue().getBteDictionary().getParamType(),
                            authLevelService, branchService, notifService, servService, trplService));
                }
            });
            Button deleteCheckActionButton = new Button(new Icon(VaadinIcon.CLOSE_SMALL));
            deleteCheckActionButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
            deleteCheckActionButton.getStyle().set("margin-top", "36.6px");
            deleteCheckActionButton.addClickListener(eventDeleteCheckAction ->{
                checkActionsLayout.remove(checkActionLine);
            });
            checkActionLine.add(checkActionForm, checkParam, deleteCheckActionButton);
            checkActionsLayout.add(checkActionLine);
        });
        buttonsLine.add(newCheckActionButton);

        this.add(abonActionLine,checkActionsLayout,buttonsLine);

    }
}
