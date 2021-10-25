package ru.tele2.autoct.views.components;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import ru.tele2.autoct.dto.TestCaseDto;
import ru.tele2.autoct.services.DownloadService;
import ru.tele2.autoct.services.TestCaseService;
import java.util.ArrayList;
import java.util.List;

public class TestCasesRepresentation extends VerticalLayout {

    List<TestCaseDto> checkedItems = new ArrayList<>();

    public TestCasesRepresentation(TestCaseService testCaseService,
                                   DownloadService downloadService,
                                   List<TestCaseDto> testCaseDtoList){
        frontFormat(this);
        this.setSpacing(false);
        //верхнюю полосу выделяем под кнопки для различных действий над ТК
        HorizontalLayout buttonsLine = new HorizontalLayout();
        frontFormat(buttonsLine);

        //кнопка выгрузки в файл
        DownloadButton downloadButton = new DownloadButton(downloadService,checkedItems);
        downloadButton.getDownloadFileButton().setEnabled(false);
        buttonsLine.add(downloadButton.getButtonWrapper());

        testCaseDtoList.forEach( testCaseDto -> {
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
                    downloadButton.getDownloadFileButton().setEnabled(false);
                } else downloadButton.getDownloadFileButton().setEnabled(true);
            });
            testCaseLine.add(checkbox, testCaseDetails);
            testCaseLine.expand(testCaseDetails);
            testCaseLine.getStyle().set("background-color", "var(--lumo-contrast-5pct)");
//            testCaseLine.getStyle().set("border-radius", "var(--lumo-border-radius-m)");
//            testCaseLine.getStyle().set("border","1px solid #b8b8b8");
            this.addComponentAsFirst(testCaseLine);
        });
        //линию кнопок в самый верх разметки
        this.addComponentAsFirst(buttonsLine);
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
