package ru.tele2.autoct.views.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import ru.tele2.autoct.dto.CheckActionDto;
import ru.tele2.autoct.dto.TestCaseStepDto;
import ru.tele2.autoct.services.additionalParams.*;
import ru.tele2.autoct.views.components.serviceViews.ConfirmRemovingFormDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class TestCaseStepForm extends VerticalLayout {


    private AbonActionForm abonActionForm;
    private List<CheckActionForm> checkActions = new ArrayList<>();
    private AtomicInteger i = new AtomicInteger(0);
    private VerticalLayout checkActionsLayout = new VerticalLayout();
    private HorizontalLayout buttonsLine = new HorizontalLayout();

    public TestCaseStepForm(TestCaseStepDto testCaseStepDto,
                            Registrator registrator){
        frontFormat(this);
        this.setWidth("85%");
        this.setSpacing(false);
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
            abonActionForm = new AbonActionForm(testCaseStepDto.getAbonAction(), checkActionsLayout, registrator);
            testCaseStepDto.getCheckActions().forEach( checkActionDto -> {
                createCheckAction(checkActionDto, registrator);
            });
            if ((abonActionForm.getAbonDictBox().getValue() != null) ) {
                if (registrator.getCheckDictionaryService().getAllByAbonDict(abonActionForm.getAbonDictBox().getValue()).size() != 0){
                    newCheckActionButton.setVisible(true);
                }
            }
        } else {
            //создается пустая форма
            abonActionForm = new AbonActionForm(null, checkActionsLayout, registrator);
        }
        abonActionForm.getAbonDictBox().addValueChangeListener( event ->{
            if ((abonActionForm.getAbonDictBox().getValue() != null) ) {
                if (registrator.getCheckDictionaryService().getAllByAbonDict(abonActionForm.getAbonDictBox().getValue()).size() != 0){
                    newCheckActionButton.setVisible(true);
                }
            } else newCheckActionButton.setVisible(false);
        });

        newCheckActionButton.addClickListener(event -> {
            createCheckAction(null, registrator);
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
                                   Registrator registrator){
        HorizontalLayout checkActionLine = new HorizontalLayout();
        frontFormat(checkActionLine);
        int pos = i.get();
        CheckActionForm checkActionForm =
                new CheckActionForm(checkActionDto, abonActionForm.getAbonDictionaryDto(), registrator);
        checkActionsLayout.add(checkActionForm);
        checkActions.add(checkActionForm);

        Button deleteCheckActionButton = new Button(new Icon(VaadinIcon.CLOSE_SMALL));
        deleteCheckActionButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        deleteCheckActionButton.getElement().setProperty("title", "Удалить действие проверки");
        deleteCheckActionButton.addClickListener(eventDeleteCheckAction ->{
            AtomicBoolean confirm = new AtomicBoolean();
            String text = new String("Уверены, что хотите удалить элемент?");
            ConfirmRemovingFormDialog confirmDialog  = new ConfirmRemovingFormDialog(confirm, text);
            confirmDialog.open();
            confirmDialog.addOpenedChangeListener( event ->{
                if (confirm.get()) {
                    checkActionsLayout.remove(checkActionLine);
                    checkActions.remove(checkActionForm);
                }
            });
        });

        Button copyCheckActionButton = new Button(new Icon(VaadinIcon.COPY_O));
        copyCheckActionButton.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        copyCheckActionButton.getElement().setProperty("title", "Копировать действие проверки");
        copyCheckActionButton.addClickListener( event -> {
//           createCheckAction(checkActions.get(pos).getCheckActionDto(), registrator);
            createCheckAction(checkActionForm.getCheckActionDto(), registrator);
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
