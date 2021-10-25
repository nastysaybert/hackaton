package ru.tele2.autoct.views.components;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import ru.tele2.autoct.dto.TestCaseDto;
import ru.tele2.autoct.views.components.helper.Row;


import java.util.ArrayList;
import java.util.List;

public class TestCaseGrid extends VerticalLayout {
    public TestCaseGrid(TestCaseDto testCase){
        this.setMargin(false);
        this.setPadding(false);
        this.setSpacing(false);
        Grid<Row> grid = new Grid<>(Row.class);
        List<Row> rows = new ArrayList<>();
        if (testCase.getInitialData() != null){
            Row initialDataRow = new Row(testCase.getInitialData().getInitialAction(),
                    testCase.getInitialData().getInitialCheck(),
                    "",
                    "");
            rows.add(initialDataRow);
        }
        testCase.getTestCaseStepList().forEach( testCaseStep -> {
            Row abonActionRow = new Row(testCaseStep.getAbonAction().getAbonDict().getAbonDictName(),
                    "",testCaseStep.getAbonAction().getBteAction() != null ?
                    testCaseStep.getAbonAction().getBteAction().getParamId():"",
                    testCaseStep.getAbonAction().getBteAction() != null ?
                            testCaseStep.getAbonAction().getBteAction().getParamValue():"");
            rows.add(abonActionRow);
            testCaseStep.getCheckActions().forEach(checkAction -> {
                Row checkActionRow = new Row("",
                        checkAction.getCheckDict().getCheckDictName(),
                        checkAction.getBteAction().getParamId(),
                        checkAction.getBteAction().getParamValue());
                rows.add(checkActionRow);
            });
        });
        grid.setItems(rows);
        grid.getColumns().get(0).setHeader("Действие абонента");
        grid.getColumns().get(1).setHeader("Проверка результата");
        grid.getColumns().get(2).setHeader("Параметр").setTextAlign(ColumnTextAlign.CENTER).setAutoWidth(true);
        grid.getColumns().get(3).setHeader("Значение параметра");
        grid.setColumnReorderingAllowed(false);
        grid.setSortableColumns();
        grid.setHeightByRows(true);
//        grid.getStyle().set("border-radius", "2px");
        grid.addThemeVariants(GridVariant.LUMO_COMPACT);
        grid.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS);
        this.add(grid);
    }
}
