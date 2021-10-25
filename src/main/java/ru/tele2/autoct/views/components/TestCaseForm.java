package ru.tele2.autoct.views.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import ru.tele2.autoct.services.additionalParams.*;
import ru.tele2.autoct.services.dictionaries.AbonDictionaryService;
import ru.tele2.autoct.services.dictionaries.CheckDictionaryService;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;


public class TestCaseForm extends VerticalLayout {

    private TextField header;
    private InitialDataForm initialDataForm;
    private TreeMap<Integer, TestCaseStepForm> stepForms = new TreeMap<>();
    private int i = 0;


    public TestCaseForm(AbonDictionaryService abonDictionaryService,
                        CheckDictionaryService checkDictionaryService,
                        AuthLevelService authLevelService,
                        BranchService branchService,
                        NotifService notifService,
                        ServService servService,
                        TrplService trplService){
        frontFormat(this);
        this.setSpacing(false);
        header = new TextField("Введите название ТК");
        header.setWidth("70%");
        //нумератор форм шагов
        HorizontalLayout initialData = new HorizontalLayout();
        VerticalLayout steps = new VerticalLayout();
        HorizontalLayout buttonsLine = new HorizontalLayout();
        frontFormat(initialData);
        frontFormat(steps);
        frontFormat(buttonsLine);
        steps.setSpacing(false);


        //Кнопка добавления исх.данных
        Button addInitialDataButton = new Button("Добавить текстовые исходные данные");
        addInitialDataButton.addClickListener(eventAdd -> {
            initialDataForm = new InitialDataForm();
            initialData.add(initialDataForm);
            addInitialDataButton.setVisible(false);
            //нажали, добавили (и скрыли кнопку добавления), хотим удалить
            //для этого добавляем рядом с исх.данными кнопку удаления
            //удалили -> делаем видимой кнопку добавления
            Button removeInitialDataButton = new Button("Удалить текстовые исходные данные");
            removeInitialDataButton.getStyle().set("margin-top", "20.6px");
            removeInitialDataButton.addClickListener(eventRemove -> {
                initialDataForm = null;
                addInitialDataButton.setVisible(true);
                initialData.removeAll();
            });
            initialData.add(removeInitialDataButton);
        });

        Button newStepButton = new Button("Добавить шаг ТК");
        newStepButton.addClickListener(event -> {
            HorizontalLayout step = new HorizontalLayout();
            frontFormat(step);
            String id = Integer.toString(i);
            step.setId(id);
            //создаем новый шаг и добавляем его в список, чтобы потом иметь доступ к данным внутри шагов
            stepForms.put(Integer.parseInt(id), new TestCaseStepForm(abonDictionaryService, checkDictionaryService,
                    authLevelService, branchService, notifService, servService, trplService));

            //добавляем последний добавленный шаг на разметку
//            step.add(stepForms.get(stepForms.size()-1));
            step.add(stepForms.get(i));
            Button removeStepButton = new Button("Удалить шаг");
            removeStepButton.getStyle().set("margin-top", "36.6px");
            removeStepButton.addClickListener(eventRemoveStep ->{
                step.removeAll();
                stepForms.remove(Integer.parseInt(id));
            });
            step.add(removeStepButton);
            steps.add(step);
            increment();
        });


        buttonsLine.add(addInitialDataButton, newStepButton);

        this.add(header, initialData, steps, buttonsLine);
    }

    public TextField getHeader(){
        return this.header;
    }

    public InitialDataForm getInitialDataForm(){
        return this.initialDataForm;
    }

    public TreeMap<Integer, TestCaseStepForm> getStepForms(){
        return this.stepForms;
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

    public boolean isValid(){
        if (getHeader().isEmpty()){
            Notification.show("Заполните наименование ТК!")
                    .addThemeVariants(NotificationVariant.LUMO_ERROR);
            return false;
        }
        if (getInitialDataForm() != null){
            if (!getInitialDataForm().isValid()){
                Notification.show("Заполните исходные данные или удалите данный блок!")
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
                return false;
            }
        }
        if (stepForms.size()<1){
            Notification.show("Введите хотя бы один шаг ТК")
                    .addThemeVariants(NotificationVariant.LUMO_ERROR);
            return false;
        }
        AtomicBoolean isValidForSteps = new AtomicBoolean(true);
        getStepForms().forEach( (integer, testCaseStepForm) -> {
            if (isValidForSteps.get() == true){
                if(!testCaseStepForm.isValid()){
                    isValidForSteps.set(false);
                    Notification.show("Заполните все поля для шагов ТК")
                            .addThemeVariants(NotificationVariant.LUMO_ERROR);
                }
            }
        });
        return isValidForSteps.get();
    }

}
