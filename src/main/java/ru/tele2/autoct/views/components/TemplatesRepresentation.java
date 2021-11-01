package ru.tele2.autoct.views.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import ru.tele2.autoct.dto.TestCaseDto;
import ru.tele2.autoct.services.DownloadService;
import ru.tele2.autoct.services.TestCaseService;
import ru.tele2.autoct.services.additionalParams.*;
import ru.tele2.autoct.services.dictionaries.AbonDictionaryService;
import ru.tele2.autoct.services.dictionaries.CheckDictionaryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TemplatesRepresentation extends VerticalLayout {
    private List<TestCaseDto> checkedItems = new ArrayList<>();
    private VerticalLayout testCaseListArea = new VerticalLayout();
    private Button deleteButton;
    private DownloadButton downloadButton;


    public TemplatesRepresentation(Map<Tab, Component> tabsToPages,
                                   Tabs tabs,
                                   Tab constructor,
                                   TestCaseService testCaseService,
                                   DownloadService downloadService,
                                   AbonDictionaryService abonDictionaryService,
                                   CheckDictionaryService checkDictionaryService,
                                   AuthLevelService authLevelService,
                                   BranchService branchService,
                                   NotifService notifService,
                                   ServService servService,
                                   TrplService trplService){
        frontFormat(this);
        this.setSpacing(false);
        frontFormat(testCaseListArea);
        testCaseListArea.setSpacing(false);
        //верхнюю полосу выделяем под кнопки для различных действий над ТК
        HorizontalLayout buttonsLine = new HorizontalLayout();
        frontFormat(buttonsLine);

        //список шаблонов
        List<TestCaseDto> templateList = testCaseService.getAllTemplates();

        //кнопка выгрузки в файл
        downloadButton = new DownloadButton(downloadService,checkedItems);
        downloadButton.getDownloadFileButton().setEnabled(false);

        //кнопка удаления ТК из БД
        deleteButton = new Button("Удалить ТК", new Icon(VaadinIcon.CLOSE_CIRCLE));
        deleteButton.setIconAfterText(true);
        deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        deleteButton.setEnabled(false);
        deleteButton.setWidth("15%");
        deleteButton.addClickListener(event -> {
            checkedItems.forEach(testCaseDto -> {
                testCaseService.delete(testCaseDto);
                getUI().get().getPage().reload();
            });
        });

        //блок для поиска
        SearchBlock searchBlock = new SearchBlock();
        searchBlock.getSearchField().getElement().addEventListener("keyup", event -> {
            searchCallback(tabsToPages, tabs, constructor, templateList, searchBlock,
                    abonDictionaryService, checkDictionaryService, authLevelService,
                    branchService, notifService, servService, trplService, testCaseService, downloadService);

        }).addEventData("element.value").setFilter("event.keyCode == 13");
        searchBlock.getSearchButton().addClickListener(event -> {
            searchCallback(tabsToPages, tabs, constructor, templateList, searchBlock,
                    abonDictionaryService, checkDictionaryService, authLevelService,
                    branchService, notifService, servService, trplService, testCaseService, downloadService);
        });


        buttonsLine.add(downloadButton.getButtonWrapper(), deleteButton, searchBlock);

        showTestCaseList(tabsToPages, tabs, constructor, testCaseListArea, templateList, "", abonDictionaryService, checkDictionaryService, authLevelService,
                branchService, notifService, servService, trplService, testCaseService, downloadService);
        testCaseListArea.getStyle().set("overflow-y","auto");
        testCaseListArea.setHeight("700px");

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
                                  AbonDictionaryService abonDictionaryService,
                                  CheckDictionaryService checkDictionaryService,
                                  AuthLevelService authLevelService,
                                  BranchService branchService,
                                  NotifService notifService,
                                  ServService servService,
                                  TrplService trplService,
                                  TestCaseService testCaseService,
                                  DownloadService downloadService){
        List <TestCaseDto> filteredList = new ArrayList<>();
        filteredList = testCaseDtoList.stream().filter(testCaseDto -> {
            return testCaseDto.getName().toLowerCase().contains(filter.toLowerCase());
        }).collect(Collectors.toList());
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
                tabsToPages.put(constructor, new TestCaseConstructorForm(testCaseDto,abonDictionaryService, checkDictionaryService, authLevelService,
                        branchService, notifService, servService, trplService, testCaseService, downloadService));
                tabs.getSelectedTab().setSelected(false);
                tabs.setSelectedTab(constructor);
            });

            buttonsLine.add(copyTemplateButton);
            gridAndButtons.add(new TestCaseGrid(testCaseDto),buttonsLine);

            //блок деталей: в заголовок название, при разворачивании табличка с ТК
            Details testCaseDetails = new Details(testCaseDto.getName(), gridAndButtons);
            testCaseDetails.addThemeVariants(DetailsVariant.REVERSE, DetailsVariant.FILLED);

            //чекбокс, чтобы можно было массово работать с несколькими ТК
            Checkbox checkbox = new Checkbox();
            checkbox.getStyle().set("margin-top", "9px");
            //выбранные добавляем в список checkedItems
            checkbox.addValueChangeListener( event ->{
                if (event.getValue()){
                    this.checkedItems.add(testCaseDto);
                } else {
                    this.checkedItems.remove(testCaseDto);
                }
                //если выбранных нет, то не даем нажать на кнопку выгрузки в ТК
                if (checkedItems.size()<1){
                    deleteButton.setEnabled(false);
                    downloadButton.getDownloadFileButton().setEnabled(false);
                } else {
                    downloadButton.getDownloadFileButton().setEnabled(true);
                    deleteButton.setEnabled(true);
                }
            });
            testCaseLine.add(checkbox, testCaseDetails);
            testCaseLine.expand(testCaseDetails);
            testCaseLine.getStyle().set("background-color", "var(--lumo-contrast-5pct)");
//            testCaseLine.getStyle().set("border-radius", "var(--lumo-border-radius-m)");
//            testCaseLine.getStyle().set("border","1px solid #b8b8b8");
            layout.addComponentAsFirst(testCaseLine);
        });
    }

    private void searchCallback (Map<Tab, Component> tabsToPages,
                                 Tabs tabs,
                                 Tab constructor,
                                 List<TestCaseDto> templateList,
                                 SearchBlock searchBlock,
                                 AbonDictionaryService abonDictionaryService,
                                 CheckDictionaryService checkDictionaryService,
                                 AuthLevelService authLevelService,
                                 BranchService branchService,
                                 NotifService notifService,
                                 ServService servService,
                                 TrplService trplService,
                                 TestCaseService testCaseService,
                                 DownloadService downloadService){
        testCaseListArea.removeAll();
        showTestCaseList(tabsToPages, tabs, constructor, testCaseListArea, templateList, searchBlock.getSearchField().getValue(),
                abonDictionaryService, checkDictionaryService, authLevelService,
                branchService, notifService, servService, trplService, testCaseService, downloadService);
    }
}
