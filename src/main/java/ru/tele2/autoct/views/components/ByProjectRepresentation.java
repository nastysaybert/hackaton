package ru.tele2.autoct.views.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import ru.tele2.autoct.dto.TestCaseDto;
import ru.tele2.autoct.dto.dictionaries.ProjectDto;
import ru.tele2.autoct.services.additionalParams.Registrator;
import ru.tele2.autoct.views.components.serviceViews.ConfirmDeletingDialog;
import ru.tele2.autoct.views.components.serviceViews.SearchBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ByProjectRepresentation extends VerticalLayout  {

    private VerticalLayout byProjectListArea = new VerticalLayout();

    public ByProjectRepresentation(Map<Tab, Component> tabsToPages,
                                   Tabs tabs,
                                   Tab constructor,
                                   Registrator registrator){
        frontFormat(this);
        this.setSpacing(false);
        frontFormat(byProjectListArea);
        byProjectListArea.setSpacing(false);
        //верхнюю полосу выделяем под кнопки для различных действий над ТК
        HorizontalLayout buttonsLine = new HorizontalLayout();
        frontFormat(buttonsLine);

        //список проектов
        List<TestCaseDto> projectList = registrator.getTestCaseService().getAll();

        //блок для поиска
        SearchBlock searchBlock = new SearchBlock();
        searchBlock.getSearchField().getElement().addEventListener("keyup", event -> {
            searchCallback(tabsToPages, tabs, constructor, projectList, searchBlock, registrator);
        }).addEventData("element.value").setFilter("event.keyCode == 13");

        searchBlock.getSearchButton().addClickListener(event -> {
            searchCallback(tabsToPages, tabs, constructor, projectList, searchBlock, registrator);
        });


//        buttonsLine.add(downloadButton.getButtonWrapper(), deleteButton, searchBlock);
        buttonsLine.add(searchBlock);

        showTestCaseList(tabsToPages, tabs, constructor, byProjectListArea, projectList, "",registrator);


        //линию кнопок в самый верх разметки
        this.add(buttonsLine, byProjectListArea);
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
//        List <ProjectDto> filteredList = new ArrayList<>();
//        filteredList = testCaseDtoList.stream()
//                .filter(testCaseDto -> testCaseDto.getName().toLowerCase().contains(filter.toLowerCase()))
//                .collect(Collectors.toList());
//        filteredList.forEach( testCaseDto -> {
        List <ProjectDto> projectList = new ArrayList<>();
        testCaseDtoList.forEach( testCaseDto -> {
            if (testCaseDto.getProject() != null){
                if (!projectList.contains(testCaseDto.getProject())){
                    projectList.add(testCaseDto.getProject());
                }
            }
        });
        List <ProjectDto> filteredProjectList = new ArrayList<>();
        filteredProjectList = projectList.stream()
                .filter(projectDto -> projectDto.getProjectName().toLowerCase().contains(filter.toLowerCase()))
                .collect(Collectors.toList());
        filteredProjectList.forEach( project -> {
            //для каждого ТК из списка своя линия разметки
            List<TestCaseDto> testCasesByProject = registrator.getTestCaseService().getAllByProject(project);
            VerticalLayout layoutByProject = new VerticalLayout();
            frontFormat(layoutByProject);
            testCasesByProject.forEach(testCaseDto -> {
                HorizontalLayout testCaseLine = new HorizontalLayout();
                frontFormat(testCaseLine);

                VerticalLayout gridAndButtons = new VerticalLayout();
                HorizontalLayout buttonsLine = new HorizontalLayout();
                frontFormat(gridAndButtons);
                gridAndButtons.setSpacing(false);
                frontFormat(buttonsLine);

                Button copyTemplateButton = new Button("Копировать ТК");
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

                Button editTemplateButton = new Button("Редактировать ТК");
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
                frontFormat(gridAndButtons);
                gridAndButtons.add(new TestCaseGrid(testCaseDto),buttonsLine);

                //блок деталей: в заголовок название, при разворачивании табличка с ТК
                Details testCaseDetails = new Details(testCaseDto.getName(), gridAndButtons);
                testCaseDetails.addThemeVariants(DetailsVariant.FILLED);
                testCaseDetails.getElement().getStyle().set("background-color", "hsla(214, 100%, 60%, 0.13)");
                testCaseLine.add(testCaseDetails);
                testCaseLine.expand(testCaseDetails);
                layoutByProject.addComponentAsFirst(testCaseLine);
            });
            Details byProjectDetails = new Details(project.getProjectName(), layoutByProject);
            byProjectDetails.addThemeVariants(DetailsVariant.FILLED);
            byProjectDetails.getElement().getStyle().set("width", "-webkit-fill-available");
            layout.add(byProjectDetails);
            layout.expand(byProjectDetails);
        });
    }

    private void searchCallback (Map<Tab, Component> tabsToPages,
                                 Tabs tabs,
                                 Tab constructor,
                                 List<TestCaseDto> testCaseList,
                                 SearchBlock searchBlock,
                                 Registrator registrator){
        byProjectListArea.removeAll();
        showTestCaseList(tabsToPages, tabs, constructor, byProjectListArea, testCaseList,
                searchBlock.getSearchField().getValue(), registrator);
    }
}
