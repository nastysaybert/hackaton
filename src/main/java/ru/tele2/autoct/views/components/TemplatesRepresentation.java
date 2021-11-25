package ru.tele2.autoct.views.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import ru.tele2.autoct.dto.TestCaseDto;
import ru.tele2.autoct.services.additionalParams.*;
import ru.tele2.autoct.views.components.serviceViews.ConfirmDeletingDialog;
import ru.tele2.autoct.views.components.serviceViews.SearchBlock;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TemplatesRepresentation extends VerticalLayout {
    private List<TestCaseDto> checkedItems = new ArrayList<>();
    private VerticalLayout testCaseListArea = new VerticalLayout();


    public TemplatesRepresentation(Map<Tab, Component> tabsToPages,
                                   Tabs tabs,
                                   Tab constructor,
                                   Registrator registrator){
        frontFormat(this);
        this.setSpacing(false);
        frontFormat(testCaseListArea);
        testCaseListArea.setSpacing(false);
        //верхнюю полосу выделяем под кнопки для различных действий над ТК
        HorizontalLayout buttonsLine = new HorizontalLayout();
        frontFormat(buttonsLine);

        //список шаблонов
        List<TestCaseDto> templateList = registrator.getTestCaseService().getAllTemplates();

        //блок для поиска
        SearchBlock searchBlock = new SearchBlock();
        searchBlock.getSearchField().getElement().addEventListener("keyup", event -> {
            searchCallback(tabsToPages, tabs, constructor, templateList, searchBlock, registrator);

        }).addEventData("element.value").setFilter("event.keyCode == 13");
        searchBlock.getSearchButton().addClickListener(event -> {
            searchCallback(tabsToPages, tabs, constructor, templateList, searchBlock, registrator);
        });


//        buttonsLine.add(downloadButton.getButtonWrapper(), deleteButton, searchBlock);
        buttonsLine.add(searchBlock);

        showTestCaseList(tabsToPages, tabs, constructor, testCaseListArea, templateList, "",registrator);


        //линию кнопок в самый верх разметки
        this.add(buttonsLine, testCaseListArea);
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

    private void showTestCaseList(Map<Tab, Component> tabsToPages,
                                  Tabs tabs,
                                  Tab constructor,
                                  VerticalLayout layout,
                                  List<TestCaseDto> testCaseDtoList,
                                  String filter,
                                  Registrator registrator){
        List <TestCaseDto> filteredList = new ArrayList<>();
        filteredList = testCaseDtoList.stream()
                .filter(testCaseDto -> testCaseDto.getName().toLowerCase().contains(filter.toLowerCase()))
                .collect(Collectors.toList());
        filteredList.forEach( testCaseDto -> {
            //для каждого ТК из списка своя линия разметки
            HorizontalLayout testCaseLine = new HorizontalLayout();
            frontFormat(testCaseLine);

            VerticalLayout gridAndButtons = new VerticalLayout();
            HorizontalLayout buttonsLine = new HorizontalLayout();
            frontFormat(gridAndButtons);
            gridAndButtons.setSpacing(false);
            frontFormat(buttonsLine);

            Button copyTemplateButton = new Button("Создать ТК по шаблону");
            copyTemplateButton.addClickListener( event -> {
                tabsToPages.remove(constructor);
                TestCaseDto copiedTemplate = new TestCaseDto();
                copiedTemplate.setTemplate(false);
                copiedTemplate.setName("");
                copiedTemplate.setInitialData(testCaseDto.getInitialData());
                copiedTemplate.setTestCaseStepList(testCaseDto.getTestCaseStepList());
                tabsToPages.put(constructor, new TestCaseConstructorForm(copiedTemplate,registrator));
                tabs.getSelectedTab().setSelected(false);
                tabs.setSelectedTab(constructor);
            });

            Button editTemplateButton = new Button("Редактировать шаблон");
            editTemplateButton.addClickListener( event -> {
                tabsToPages.remove(constructor);
                tabsToPages.put(constructor, new TestCaseConstructorForm(testCaseDto,registrator));
                tabs.getSelectedTab().setSelected(false);
                tabs.setSelectedTab(constructor);
            });

            Button deleteTemplateButton = new Button(new Icon(VaadinIcon.TRASH));
            deleteTemplateButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
            deleteTemplateButton.addClickListener( event -> {
                List<TestCaseDto> deletingList = new ArrayList<>();
                deletingList.add(testCaseDto);
                new ConfirmDeletingDialog(deletingList,registrator.getTestCaseService()).open();
            });



            buttonsLine.add(copyTemplateButton, editTemplateButton, deleteTemplateButton);
            gridAndButtons.add(new TestCaseGrid(testCaseDto),buttonsLine);

            //блок деталей: в заголовок название, при разворачивании табличка с ТК
            Details testCaseDetails = new Details(testCaseDto.getName(), gridAndButtons);
//            testCaseDetails.addThemeVariants(DetailsVariant.REVERSE, DetailsVariant.FILLED);
            testCaseDetails.addThemeVariants(DetailsVariant.FILLED);
            testCaseDetails.getElement().getStyle().set("background-color", "hsla(214, 100%, 60%, 0.13)");

            testCaseLine.add(testCaseDetails);
            testCaseLine.expand(testCaseDetails);
            layout.addComponentAsFirst(testCaseLine);
        });
    }

    private void searchCallback (Map<Tab, Component> tabsToPages,
                                 Tabs tabs,
                                 Tab constructor,
                                 List<TestCaseDto> templateList,
                                 SearchBlock searchBlock,
                                 Registrator registrator){
        testCaseListArea.removeAll();
        showTestCaseList(tabsToPages, tabs, constructor, testCaseListArea, templateList,
                searchBlock.getSearchField().getValue(), registrator);
    }
}
