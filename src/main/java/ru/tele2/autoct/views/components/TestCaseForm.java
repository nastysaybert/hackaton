package ru.tele2.autoct.views.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import ru.tele2.autoct.dto.TestCaseDto;
import ru.tele2.autoct.dto.TestCaseStepDto;
import ru.tele2.autoct.dto.dictionaries.ProjectDto;
import ru.tele2.autoct.services.additionalParams.*;
import ru.tele2.autoct.views.components.serviceViews.ConfirmRemovingFormDialog;
import ru.tele2.autoct.views.components.serviceViews.ProjectForm;

import java.awt.*;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;


public class TestCaseForm extends VerticalLayout {

    private TextField header;
    private InitialDataForm initialDataForm;
    private TreeMap<Integer, TestCaseStepForm> stepForms = new TreeMap<>();
    private VerticalLayout steps = new VerticalLayout();
    private HorizontalLayout headerAndIsTemplate = new HorizontalLayout();
    private HorizontalLayout buttonsLine = new HorizontalLayout();
    private HorizontalLayout initialData = new HorizontalLayout();
    private Button addInitialDataButton = new Button("Добавить текстовые исходные данные");
    private Checkbox isTemplate = new Checkbox();
    private ProjectForm projectForm;
    private int i = 0;
    private Long id = Long.valueOf(-1);


    public TestCaseForm(TestCaseDto testCaseDto,
                        Registrator registrator){
        frontFormat(this);
//        this.getStyle().set("background-color", "var(--lumo-contrast-5pct)");
        this.setSpacing(false);
        this.getStyle().set("overflow-y","auto");
        this.setHeight("90vh");
        header = new TextField("Введите название ТК");
        header.setWidth("85%");


        frontFormat(headerAndIsTemplate);
        frontFormat(initialData);
        frontFormat(steps);
        frontFormat(buttonsLine);
        steps.setSpacing(false);

        isTemplate.setLabel("Шаблон");
//        isTemplate.set("Шаблон");

        projectForm = new ProjectForm(registrator.getProjectService());
        headerAndIsTemplate.add(isTemplate, projectForm);

        //Кнопка добавления исх.данных
        addInitialDataButton.addClickListener(eventAdd -> {
            createInitialData();
        });

        Button newStepButton = new Button("Добавить шаг ТК");
        newStepButton.addClickListener(event -> {
            createStep(null, registrator);
        });

        if (testCaseDto!=null){
            //если заполнен id (т.е. ТК уже сохранялся)
            //и ТК создан не по шаблону, то сохранить его id
            if (testCaseDto.getId()!=null){
                id = testCaseDto.getId();
            }

            header.setValue(testCaseDto.getName());
            isTemplate.setValue(testCaseDto.isTemplate());
            projectForm.setProject(testCaseDto.getProject());

            if (testCaseDto.getInitialData()!=null){
                createInitialData();
                initialDataForm.setValue(testCaseDto.getInitialData());
            }
            testCaseDto.getTestCaseStepList().forEach( testCaseStepDto -> {
                createStep(testCaseStepDto, registrator);
            });
        }


        buttonsLine.add(newStepButton, addInitialDataButton);

//        this.add(headerAndIsTemplate, isTemplate, projectForm, initialData, steps, buttonsLine);
        this.add(header, headerAndIsTemplate, initialData, steps, buttonsLine);
    }

    public void createInitialData(){
        initialDataForm = new InitialDataForm();
        initialData.add(initialDataForm);
        addInitialDataButton.setVisible(false);
        //нажали, добавили (и скрыли кнопку добавления), хотим удалить
        //для этого добавляем рядом с исх.данными кнопку удаления
        //удалили -> делаем видимой кнопку добавления
        Button removeInitialDataButton = new Button(new Icon(VaadinIcon.CLOSE_SMALL));
        removeInitialDataButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        removeInitialDataButton.getElement().setProperty("title", "Удалить текстовые исходные данные");
        removeInitialDataButton.getStyle().set("margin-top", "20.6px");
        removeInitialDataButton.addClickListener(eventRemove -> {
            AtomicBoolean confirm = new AtomicBoolean();
            String text = new String("Уверены, что хотите удалить элемент?");
            ConfirmRemovingFormDialog confirmDialog  = new ConfirmRemovingFormDialog(confirm, text);
            confirmDialog.open();
            confirmDialog.addOpenedChangeListener( event ->{
                if (confirm.get()) {
                    initialDataForm = null;
                    addInitialDataButton.setVisible(true);
                    initialData.removeAll();
                }
            });
        });
        initialData.add(removeInitialDataButton);
    }

    public TextField getHeader(){
        return this.header;
    }

    public Boolean isTemplate(){
        return this.isTemplate.getValue();
    }

    public InitialDataForm getInitialDataForm(){
        return this.initialDataForm;
    }

    public TreeMap<Integer, TestCaseStepForm> getStepForms(){
        return this.stepForms;
    }

    public Long getTestCaseId(){
        return id;
    }

    public ProjectForm getProjectForm(){
        return projectForm;
    }

    private void createStep(TestCaseStepDto testCaseStepDto,
                            Registrator registrator){
        HorizontalLayout step = new HorizontalLayout();
        frontFormat(step);
        String id = Integer.toString(i);
        step.setId(id);
        //создаем новый шаг и добавляем его в список, чтобы потом иметь доступ к данным внутри шагов
        stepForms.put(Integer.parseInt(id), new TestCaseStepForm(testCaseStepDto, registrator));

        //добавляем последний добавленный шаг на разметку
        step.add(stepForms.get(i));
        Button removeStepButton = new Button(new Icon(VaadinIcon.CLOSE_SMALL));
        removeStepButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        removeStepButton.getElement().setProperty("title", "Удалить шаг");
        removeStepButton.addClickListener(eventRemoveStep ->{
            AtomicBoolean confirm = new AtomicBoolean();
            String text = new String("Уверены, что хотите удалить элемент?");
            ConfirmRemovingFormDialog confirmDialog  = new ConfirmRemovingFormDialog(confirm, text);
            confirmDialog.open();
            confirmDialog.addOpenedChangeListener( event ->{
                if (confirm.get()) {
                    step.removeAll();
                    stepForms.remove(Integer.parseInt(id));
                }
            });
        });

        Button copyStepButton = new Button(new Icon(VaadinIcon.COPY_O));
        copyStepButton.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        copyStepButton.getElement().setProperty("title", "Копировать шаг");
        copyStepButton.addClickListener(eventRemoveStep ->{
            createStep(stepForms.get(Integer.parseInt(id)).getTestCaseStepDto(), registrator);
        });

        step.add(removeStepButton, copyStepButton);
        steps.add(step);
        increment();

    }

    private void frontFormat (VerticalLayout component){
        component.setPadding(false);
        component.setMargin(false);
        component.setWidthFull();
    }

    private void frontFormat (HorizontalLayout component){
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

        if (getProjectForm().isEmpty()){
            Notification.show("Заполните проект!")
                    .addThemeVariants(NotificationVariant.LUMO_ERROR);
            return false;
        }

        if (getHeader().getValue().length() > 170){
            Notification.show("Слишком длинное название ТК")
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
