package ru.tele2.autoct.views.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.internal.Pair;
import ru.tele2.autoct.dto.AbonActionDto;
import ru.tele2.autoct.dto.CheckActionDto;
import ru.tele2.autoct.dto.TestCaseStepDto;
import ru.tele2.autoct.services.additionalParams.*;
import ru.tele2.autoct.services.dictionaries.AbonDictionaryService;
import ru.tele2.autoct.services.dictionaries.CheckDictionaryService;
import ru.tele2.autoct.views.components.additionalParams.AdditionalParam;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class TestCaseStepForm extends VerticalLayout {

    //    private TreeMap <Integer, Pair<CheckActionForm, AdditionalParam>> checkActions = new TreeMap<>();
//    private Pair<AbonActionForm, AdditionalParam> abonAction;
    private AbonActionForm abonActionForm;
    private List<CheckActionForm> checkActions = new ArrayList<>();
    private AtomicInteger i = new AtomicInteger(0);

    public TestCaseStepForm(
                            AbonDictionaryService abonDictionaryService,
                            CheckDictionaryService checkDictionaryService,
                            AuthLevelService authLevelService,
                            BranchService branchService,
                            NotifService notifService,
                            ServService servService,
                            TrplService trplService){
        frontFormat(this);
        this.setWidth("70%");
        this.setSpacing(false);
//        HorizontalLayout abonActionLine = new HorizontalLayout();
        VerticalLayout checkActionsLayout = new VerticalLayout();
        HorizontalLayout buttonsLine = new HorizontalLayout();
//        frontFormat(abonActionLine);
        frontFormat(checkActionsLayout);
        frontFormat(buttonsLine);
        checkActionsLayout.setSpacing(false);
        checkActionsLayout.getStyle().set("padding-left", "5%");
        Div param = new Div();
        param.setWidth("40%");

//        checkActionsLayout.getStyle().set("border", "2px solid red");

        Button newCheckActionButton = new Button("Добавить действие проверки");
        newCheckActionButton.setVisible(false);

        //создается пустая форма
        abonActionForm = new AbonActionForm(null, checkActionsLayout, abonDictionaryService,
                authLevelService, branchService, notifService, servService, trplService);
        abonActionForm.getAbonDictBox().addValueChangeListener( event ->{
            if ((abonActionForm.getAbonDictBox().getValue() != null) ) {
                if (checkDictionaryService.getAllByAbonDict(abonActionForm.getAbonDictBox().getValue()).size() != 0){
                    newCheckActionButton.setVisible(true);
                }
            } else newCheckActionButton.setVisible(false);
        });

        newCheckActionButton.addClickListener(event -> {
            HorizontalLayout checkActionLine = new HorizontalLayout();
            frontFormat(checkActionLine);
            int pos = i.get();
            CheckActionForm checkActionForm = new CheckActionForm(null, abonActionForm.getAbonDictionaryDto(),
                    checkDictionaryService, authLevelService, branchService, notifService, servService, trplService);
            checkActionsLayout.add(checkActionForm);
            checkActions.add(checkActionForm);
            Button deleteCheckActionButton = new Button(new Icon(VaadinIcon.CLOSE_SMALL));
            deleteCheckActionButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
            deleteCheckActionButton.getStyle().set("margin-top", "36.6px");
            deleteCheckActionButton.addClickListener(eventDeleteCheckAction ->{
                checkActionsLayout.remove(checkActionLine);
                checkActions.remove(pos);
            });
            checkActionLine.add(checkActionForm,deleteCheckActionButton);
            checkActionsLayout.add(checkActionLine);
            i.incrementAndGet();
//            HorizontalLayout checkActionLine = new HorizontalLayout();
//            frontFormat(checkActionLine);
//            CheckActionForm checkActionForm =
//                    new CheckActionForm(abonActionForm.getAbonDictBox().getValue(), checkDictionaryService);
//            checkActionForm.setWidth("61%");
//            String id = Integer.toString(i);
//            Div checkActionParamWrapper = new Div();
//            checkActionParamWrapper.setWidth("39%");
//
//
//            checkActionForm.getCheckDictBox().addValueChangeListener(element ->{
//                checkActions.remove(Integer.parseInt(id));
//                checkActionParamWrapper.removeAll();
//                if (element.getValue() != null) {
//                    Pair <CheckActionForm,AdditionalParam> pairCheckAndParam =
//                            new Pair<>(checkActionForm, new AdditionalParam(element.getValue().getBteDictionary().getParamType(),
//                                    authLevelService, branchService, notifService, servService, trplService));
//                    checkActionParamWrapper.add(pairCheckAndParam.getSecond());
//                    checkActions.put(Integer.parseInt(id), pairCheckAndParam);
//                }
//            });

//            checkActionLine.add(checkActionForm, deleteCheckActionButton);
//            checkActionsLayout.add(checkActionLine);
//            increment();
        });
        buttonsLine.add(newCheckActionButton);

        this.add(abonActionForm,checkActionsLayout,buttonsLine);
    }

//    public TestCaseStepForm copyStep(AbonDictionaryService abonDictionaryService,
//                                     CheckDictionaryService checkDictionaryService,
//                                     AuthLevelService authLevelService,
//                                     BranchService branchService,
//                                     NotifService notifService,
//                                     ServService servService,
//                                     TrplService trplService){
//        TestCaseStepForm copiedStepForm = new TestCaseStepForm(abonDictionaryService, checkDictionaryService,
//                authLevelService, branchService, notifService, servService, trplService);
//        AbonActionForm copiedAbonAction = new AbonActionForm(abonDictionaryService);
//        copiedStepForm.getAbonAction().getFirst().setAbonDictionaryDto(
//                this.getAbonAction().getFirst().getAbonDictionaryDto());
//        if (this.getAbonAction().getFirst().getComment()!=""){
//            copiedStepForm.getAbonAction().getFirst().setComment(
//                    this.getAbonAction().getFirst().getComment());
//        }
//        if(this.getAbonAction().getSecond()!=null){
//            copiedStepForm.getAbonAction().getSecond().setAdditionalParam(this.getAbonAction().getSecond().getAdditionalParamDto(),
//                    copiedStepForm.getAbonAction().getFirst().getAbonDictionaryDto().getBteDictionary()
//                    .getParamType(),authLevelService, branchService, notifService, servService, trplService);
//        }
//            copiedStepForm.setAbonAction(new Pair<>());
//
//                    new AdditionalParam(copiedStepForm.getAbonAction().getFirst().getAbonDictionaryDto().getBteDictionary()
//                            .getParamType(),authLevelService, branchService, notifService, servService, trplService);
//            additionalParam.setAdditionalParam(source.getAbonAction().getSecond().getAdditionalParamDto(),
//                    copiedStepForm.getAbonAction().getFirst().getAbonDictionaryDto().getBteDictionary()
//                    .getParamType(),authLevelService, branchService, notifService, servService, trplService);
//
//        }
//
//        source.getCheckActions().forEach();
//
//
//        copiedStepForm.abonAction = source.getAbonAction();
//        source.checkActions.forEach( (integer, checkActionFormAdditionalParamPair) -
//        });
//        return copiedStepForm;
//    }

//    public void setAbonAction(Pair<AbonActionForm, AdditionalParam> pair){
//        this.abonAction = pair;
//    }

//    public Pair<AbonActionForm, AdditionalParam> getAbonAction(){
//        return this.abonAction;
//    }

//    public TreeMap <Integer, Pair<CheckActionForm, AdditionalParam>> getCheckActions(){
//        return this.checkActions;
//    }

    public TestCaseStepDto getTestCaseStepDto(){
        TestCaseStepDto result = new TestCaseStepDto();
        result.setAbonAction(getAbonActionForm().getAbonActionDto());
        List<CheckActionDto> checkActions = new ArrayList<>();
        getCheckActions().forEach(checkActionForm -> {
            checkActions.add(checkActionForm.getCheckActionDto());
        });
        result.setCheckActions(checkActions);
        return result;
    }

    public boolean isValid(){
        if (getAbonActionForm()!=null){
            if (!getAbonActionForm().isValid()){
                return false;
            }
        } else return false;

        AtomicBoolean isValidForCheckActions = new AtomicBoolean(true);
        getCheckActions().forEach( checkAction ->{
            if (checkAction != null){
                if (!checkAction.isValid()){
                    isValidForCheckActions.set(false);
                    return;
                }
            } else return;
        });
        return isValidForCheckActions.get();
    }

    public AbonActionForm getAbonActionForm(){
        return this.abonActionForm;
    }

    public List<CheckActionForm> getCheckActions(){
        return this.checkActions;
    }

//    public boolean isValid(){
//        if (getAbonAction()!=null){
//            if (!getAbonAction().getFirst().isValid()){
//                return false;
//            }
//        } else return false;
//
//        if (getAbonAction().getSecond()!=null){
//            if (!getAbonAction().getSecond().isValid()){
//                return false;
//            }
//        }
//
//        AtomicBoolean isValidForCheckActions = new AtomicBoolean(true);
//        getCheckActions().forEach( (id, pair)->{
//            if (pair.getFirst() != null){
//                if (!pair.getFirst().isValid() || !pair.getSecond().isValid()){
//                    isValidForCheckActions.set(false);
//                    return;
//                }
//            } else return;
//        });
//        return isValidForCheckActions.get();
//    }

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
