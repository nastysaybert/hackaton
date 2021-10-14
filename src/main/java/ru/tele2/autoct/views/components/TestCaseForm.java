package ru.tele2.autoct.views.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import ru.tele2.autoct.services.additionalParams.*;
import ru.tele2.autoct.services.dictionaries.AbonDictionaryService;
import ru.tele2.autoct.services.dictionaries.CheckDictionaryService;


public class TestCaseForm extends VerticalLayout {
    public TestCaseForm(AbonDictionaryService abonDictionaryService,
                        CheckDictionaryService checkDictionaryService,
                        AuthLevelService authLevelService,
                        BranchService branchService,
                        NotifService notifService,
                        ServService servService,
                        TrplService trplService){
        this.setMargin(false);
        this.setPadding(false);
        this.setSpacing(false);
        this.setWidthFull();
        TextField header = new TextField("Введите название ТК");
        header.setWidth("70%");
        HorizontalLayout initialData = new HorizontalLayout();
        VerticalLayout steps = new VerticalLayout();
        HorizontalLayout buttonsLine = new HorizontalLayout();
        initialData.setMargin(false);
        initialData.setPadding(false);
        initialData.setWidthFull();
        steps.setMargin(false);
        steps.setPadding(false);
        steps.setSpacing(false);
        steps.setWidthFull();
        buttonsLine.setMargin(false);
        buttonsLine.setPadding(false);
        buttonsLine.setWidthFull();

        //Кнопка добавления исх.данных
        Button addInitialDataButton = new Button("Добавить текстовые исходные данные");
        addInitialDataButton.addClickListener(eventAdd -> {
            initialData.add(new InitialDataForm());
            addInitialDataButton.setVisible(false);
            //нажали, добавили (и скрыли кнопку добавления), хотим удалить
            //для этого добавляем рядом с исх.данными кнопку удаления
            //удалили -> делаем видимой кнопку добавления
            Button removeInitialData = new Button("Удалить текстовые исходные данные");
            removeInitialData.getStyle().set("margin-top", "20.6px");
            removeInitialData.addClickListener(eventRemove -> {
                addInitialDataButton.setVisible(true);
                initialData.removeAll();
            });
            initialData.add(removeInitialData);
        });

        Button newStepButton = new Button("Добавить шаг ТК");
        newStepButton.addClickListener(event -> {
            HorizontalLayout step = new HorizontalLayout();
            step.setPadding(false);
            step.setMargin(false);
            step.setWidthFull();
            step.add(new TestCaseStepForm(abonDictionaryService, checkDictionaryService,
                    authLevelService, branchService, notifService, servService, trplService));
            Button removeStepButton = new Button("Удалить шаг");
            removeStepButton.getStyle().set("margin-top", "36.6px");
            removeStepButton.addClickListener(eventRemoveStep ->{
                step.removeAll();
            });
            step.add(removeStepButton);
            steps.add(step);
        });

        buttonsLine.add(addInitialDataButton, newStepButton);

        this.add(header, initialData, steps, buttonsLine);
    }
}
