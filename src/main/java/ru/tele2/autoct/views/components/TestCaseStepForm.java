package ru.tele2.autoct.views.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dnd.EffectAllowed;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.internal.Pair;
import ru.tele2.autoct.services.additionalParams.*;
import ru.tele2.autoct.services.dictionaries.AbonDictionaryService;
import ru.tele2.autoct.services.dictionaries.CheckDictionaryService;
import ru.tele2.autoct.views.components.additionalParams.AdditionalParam;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class TestCaseStepForm extends VerticalLayout {

    private Pair<AbonActionForm, AdditionalParam> abonAction;
    private TreeMap <Integer, Pair<CheckActionForm, AdditionalParam>> checkActions = new TreeMap<>();
    private int i = 0;

    public TestCaseStepForm(AbonDictionaryService abonDictionaryService,
                            CheckDictionaryService checkDictionaryService,
                            AuthLevelService authLevelService,
                            BranchService branchService,
                            NotifService notifService,
                            ServService servService,
                            TrplService trplService){
        frontFormat(this);
        this.setWidth("70%");
        this.setSpacing(false);
        HorizontalLayout abonActionLine = new HorizontalLayout();
        VerticalLayout checkActionsLayout = new VerticalLayout();
        HorizontalLayout buttonsLine = new HorizontalLayout();
        frontFormat(abonActionLine);
        frontFormat(checkActionsLayout);
        frontFormat(buttonsLine);
        checkActionsLayout.setSpacing(false);
        checkActionsLayout.getStyle().set("padding-left", "5%");
        Div param = new Div();
        param.setWidth("40%");
        //нумератор форм проверок по действию


//        checkActionsLayout.getStyle().set("border", "2px solid red");


        Button newCheckActionButton = new Button("Добавить действие проверки");
        newCheckActionButton.setVisible(false);

        AbonActionForm abonActionForm = new AbonActionForm(abonDictionaryService);
        abonActionForm.setWidth("60%");
        abonActionForm.addValueChangeListener(element ->{
            param.removeAll();
            checkActionsLayout.removeAll();
            if ((element.getValue() != null) ) {
                abonAction = new Pair(abonActionForm,null);
                if (element.getValue().getBteDictionary() != null){
                    abonAction = new Pair(abonActionForm,new AdditionalParam(element.getValue().getBteDictionary().getParamType(),
                            authLevelService, branchService, notifService, servService, trplService));
                    param.add(abonAction.getSecond());
                }
                if (checkDictionaryService.getAllByAbonDict(element.getValue()).size() != 0){
                    newCheckActionButton.setVisible(true);
                }
            } else newCheckActionButton.setVisible(false);
        });
        abonActionLine.add(abonActionForm, param);

        newCheckActionButton.addClickListener(event -> {
            HorizontalLayout checkActionLine = new HorizontalLayout();
            frontFormat(checkActionLine);
            CheckActionForm checkActionForm = new CheckActionForm(abonActionForm.getValue(), checkDictionaryService);
            checkActionForm.setWidth("61%");
            String id = Integer.toString(i);
            Div checkActionParamWrapper = new Div();
            checkActionParamWrapper.setWidth("39%");


            checkActionForm.addValueChangeListener(element ->{
                checkActions.remove(Integer.parseInt(id));
                checkActionParamWrapper.removeAll();
                if (element.getValue() != null) {
                    Pair <CheckActionForm,AdditionalParam> pairCheckAndParam =
                            new Pair<>(checkActionForm, new AdditionalParam(element.getValue().getBteDictionary().getParamType(),
                                    authLevelService, branchService, notifService, servService, trplService));
                    checkActionParamWrapper.add(pairCheckAndParam.getSecond());
                    checkActions.put(Integer.parseInt(id), pairCheckAndParam);
                }
            });
            Button deleteCheckActionButton = new Button(new Icon(VaadinIcon.CLOSE_SMALL));
            deleteCheckActionButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
            deleteCheckActionButton.getStyle().set("margin-top", "36.6px");
            deleteCheckActionButton.addClickListener(eventDeleteCheckAction ->{
                checkActionsLayout.remove(checkActionLine);
                checkActions.remove(Integer.parseInt(id));
            });
            checkActionLine.add(checkActionForm, checkActionParamWrapper, deleteCheckActionButton);
            checkActionsLayout.add(checkActionLine);
            increment();
        });
        buttonsLine.add(newCheckActionButton);

        this.add(abonActionLine,checkActionsLayout,buttonsLine);

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

    private void increment(){
        this.i++;
    }

    public Pair<AbonActionForm, AdditionalParam> getAbonAction(){
        return this.abonAction;
    }

    public TreeMap <Integer, Pair<CheckActionForm, AdditionalParam>> getCheckActions(){
        return this.checkActions;
    }

    public boolean isValid(){
        if (getAbonAction()!=null){
            if (!getAbonAction().getFirst().isValid()){
                return false;
            }
        } else return false;

        if (getAbonAction().getSecond()!=null){
            if (!getAbonAction().getSecond().isValid()){
                return false;
            }
        }

        AtomicBoolean isValidForCheckActions = new AtomicBoolean(true);
        getCheckActions().forEach( (id, pair)->{
            if (pair.getFirst() != null){
                if (!pair.getFirst().isValid() || !pair.getSecond().isValid()){
                    isValidForCheckActions.set(false);
                    return;
                }
            } else return;
        });
        return isValidForCheckActions.get();
    }
}
