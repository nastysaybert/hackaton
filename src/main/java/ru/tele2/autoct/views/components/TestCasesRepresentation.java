package ru.tele2.autoct.views.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import ru.tele2.autoct.dto.TestCaseDto;
import ru.tele2.autoct.services.DownloadService;
import ru.tele2.autoct.services.TestCaseService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestCasesRepresentation extends VerticalLayout {

    private List<TestCaseDto> checkedItems = new ArrayList<>();
    private VerticalLayout testCaseListArea = new VerticalLayout();
    private Button deleteButton;
    private DownloadButton downloadButton;


    public TestCasesRepresentation(TestCaseService testCaseService,
                                   DownloadService downloadService,
                                   List<TestCaseDto> testCaseDtoList){
        frontFormat(this);
        this.setSpacing(false);
        frontFormat(testCaseListArea);
        testCaseListArea.setSpacing(false);
        //верхнюю полосу выделяем под кнопки для различных действий над ТК
        HorizontalLayout buttonsLine = new HorizontalLayout();
        frontFormat(buttonsLine);

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
            searchCallback(testCaseDtoList, searchBlock);

        }).addEventData("element.value").setFilter("event.keyCode == 13");
        searchBlock.getSearchButton().addClickListener(event -> {
            searchCallback(testCaseDtoList, searchBlock);
        });


        buttonsLine.add(downloadButton.getButtonWrapper(), deleteButton, searchBlock);

        showTestCaseList(testCaseListArea, testCaseDtoList, "");
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

    private void showTestCaseList(VerticalLayout layout, List<TestCaseDto> testCaseDtoList, String filter){
        List <TestCaseDto> filteredList = new ArrayList<>();
        filteredList = testCaseDtoList.stream().filter(testCaseDto -> {
            return testCaseDto.getName().toLowerCase().contains(filter.toLowerCase());
        }).collect(Collectors.toList());
        filteredList.forEach( testCaseDto -> {
            //для каждого ТК из списка своя линия разметки
            HorizontalLayout testCaseLine = new HorizontalLayout();
            frontFormat(testCaseLine);

            //блок деталей: в заголовок название, при разворачивании табличка с ТК
            Details testCaseDetails = new Details(testCaseDto.getName(), new TestCaseGrid(testCaseDto));
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

    private void searchCallback (List<TestCaseDto> testCaseDtoList, SearchBlock searchBlock){
        testCaseListArea.removeAll();
        showTestCaseList(testCaseListArea, testCaseDtoList, searchBlock.getSearchField().getValue());
    }
}
