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


    private AbonActionForm abonActionForm;
    private List<CheckActionForm> checkActions = new ArrayList<>();
    private AtomicInteger i = new AtomicInteger(0);
    private VerticalLayout checkActionsLayout = new VerticalLayout();
    private HorizontalLayout buttonsLine = new HorizontalLayout();

    public TestCaseStepForm(TestCaseStepDto testCaseStepDto,
                            AbonDictionaryService abonDictionaryService,
                            CheckDictionaryService checkDictionaryService,
                            AuthLevelService authLevelService,
                            BranchService branchService,
                            NotifService notifService,
                            ServService servService,
                            TrplService trplService){
        frontFormat(this);
        this.setWidth("85%");
        this.setSpacing(false);
//        this.getStyle().set("background-color", "var(--lumo-contrast-5pct)");
//        this.getStyle().set("background-color", "white");
        this.getStyle().set("border-radius", "10px 10px 10px 10px");
        this.getStyle().set("border", "2px solid var(--lumo-primary-color-10pct)");
        this.getStyle().set("padding-left", "10px");
        this.getStyle().set("padding-right", "10px");
        this.getStyle().set("padding-bottom", "10px");
        this.getStyle().set("margin-bottom", "5px");
        frontFormat(checkActionsLayout);
        frontFormat(buttonsLine);
        checkActionsLayout.setSpacing(false);
        checkActionsLayout.getStyle().set("padding-left", "5%");

        Button newCheckActionButton = new Button("Добавить действие проверки");
        newCheckActionButton.setVisible(false);

        if (testCaseStepDto != null){
            abonActionForm = new AbonActionForm(testCaseStepDto.getAbonAction(), checkActionsLayout, abonDictionaryService,
                    authLevelService, branchService, notifService, servService, trplService);
            testCaseStepDto.getCheckActions().forEach( checkActionDto -> {
                createCheckAction(checkActionDto,
                        checkDictionaryService, authLevelService, branchService, notifService, servService, trplService);
            });
            if ((abonActionForm.getAbonDictBox().getValue() != null) ) {
                if (checkDictionaryService.getAllByAbonDict(abonActionForm.getAbonDictBox().getValue()).size() != 0){
                    newCheckActionButton.setVisible(true);
                }
            }
        } else {
            //создается пустая форма
            abonActionForm = new AbonActionForm(null, checkActionsLayout, abonDictionaryService,
                    authLevelService, branchService, notifService, servService, trplService);
        }
        abonActionForm.getAbonDictBox().addValueChangeListener( event ->{
            if ((abonActionForm.getAbonDictBox().getValue() != null) ) {
                if (checkDictionaryService.getAllByAbonDict(abonActionForm.getAbonDictBox().getValue()).size() != 0){
                    newCheckActionButton.setVisible(true);
                }
            } else newCheckActionButton.setVisible(false);
        });

        newCheckActionButton.addClickListener(event -> {
            createCheckAction(null,
                    checkDictionaryService, authLevelService, branchService, notifService, servService, trplService);
        });
        buttonsLine.add(newCheckActionButton);
        this.add(abonActionForm,checkActionsLayout,buttonsLine);
    }

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

    public void createCheckAction (CheckActionDto checkActionDto,
                                   CheckDictionaryService checkDictionaryService,
                                   AuthLevelService authLevelService,
                                   BranchService branchService,
                                   NotifService notifService,
                                   ServService servService,
                                   TrplService trplService){
        HorizontalLayout checkActionLine = new HorizontalLayout();
        frontFormat(checkActionLine);
        int pos = i.get();
        CheckActionForm checkActionForm = new CheckActionForm(checkActionDto, abonActionForm.getAbonDictionaryDto(),
                checkDictionaryService, authLevelService, branchService, notifService, servService, trplService);
        checkActionsLayout.add(checkActionForm);
        checkActions.add(checkActionForm);

        Button deleteCheckActionButton = new Button(new Icon(VaadinIcon.CLOSE_SMALL));
        deleteCheckActionButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        deleteCheckActionButton.getStyle().set("margin-top", "36.6px");
        deleteCheckActionButton.getElement().setProperty("title", "Удалить действие проверки");
        deleteCheckActionButton.addClickListener(eventDeleteCheckAction ->{
            checkActionsLayout.remove(checkActionLine);
            checkActions.remove(pos);
        });

        Button copyCheckActionButton = new Button(new Icon(VaadinIcon.COPY_O));
        copyCheckActionButton.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        copyCheckActionButton.getStyle().set("margin-top", "36.6px");
        copyCheckActionButton.getElement().setProperty("title", "Копировать действие проверки");
        copyCheckActionButton.addClickListener( event -> {
           createCheckAction(checkActions.get(pos).getCheckActionDto(), checkDictionaryService, authLevelService,
                   branchService, notifService, servService, trplService);
        });

        checkActionLine.add(checkActionForm,deleteCheckActionButton, copyCheckActionButton);
        checkActionsLayout.add(checkActionLine);
        i.incrementAndGet();
    }

    public AbonActionForm getAbonActionForm(){
        return this.abonActionForm;
    }

    public List<CheckActionForm> getCheckActions(){
        return this.checkActions;
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
