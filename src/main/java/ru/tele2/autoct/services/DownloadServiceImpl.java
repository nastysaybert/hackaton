package ru.tele2.autoct.services;

import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;
import ru.tele2.autoct.dto.CheckActionDto;
import ru.tele2.autoct.dto.TestCaseDto;
import ru.tele2.autoct.dto.TestCaseStepDto;

import javax.persistence.Column;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class DownloadServiceImpl implements DownloadService {
    public File download(List<TestCaseDto> downloadList){
        XSSFWorkbook xlsx = new XSSFWorkbook();
        XSSFSheet sheet = xlsx.createSheet("TestCases");
        int rowNum = 0;

        Row row = sheet.createRow(rowNum++);
        //шапка таблицы
        Cell cell = row.createCell(0);
        cell.setCellValue("№ ТК");
        cell = row.createCell(1);
        cell.setCellValue("Шаг ТК");
        cell = row.createCell(2);
        cell.setCellValue("Действие абонента");
        cell = row.createCell(3);
        cell.setCellValue("Проверка результата (действие проверки)");
        cell = row.createCell(4);
        cell.setCellValue("Название Ключевого параметра");
        cell = row.createCell(5);
        cell.setCellValue("Значение Ключевого параметра");
        cell = row.createCell(6);
        cell.setCellValue("Наименование услуги/тарифа/Справка");
        cell = row.createCell(7);
        cell.setCellValue("Действие BTE");
        cell = row.createCell(8);
        cell.setCellValue("Комментарий");
        cell = row.createCell(9);
        cell.setCellValue("Номер 1");
        cell = row.createCell(10);
        cell.setCellValue("Номер 2");
        cell = row.createCell(11);
        cell.setCellValue("Номер 3");
        for (int i = 0; i<downloadList.size(); i++) {
            TestCaseDto testCase = downloadList.get(i);
            //название ТК в 1ю ячейку
            row = sheet.createRow(rowNum++);
            row.setRowStyle(headerStyle(xlsx));
            cell = row.createCell(0);
            cell.setCellStyle(headerStyle(xlsx));
            cell.setCellValue(testCase.getName());
            if (testCase.getInitialData() != null) {
                int colNum = 0;
                //пишем с новой строки
                row = sheet.createRow(rowNum++);
                row.setRowStyle(initialDataStyle(xlsx));
                //номер ТК
                cell = row.createCell(colNum++);
                cell.setCellValue(i+1);
                cell.setCellStyle(initialDataStyle(xlsx));
                //номер шага
                cell = row.createCell(colNum++);
                cell.setCellValue(0);
                cell.setCellStyle(initialDataStyle(xlsx));
                //действие
                cell = row.createCell(colNum++);
                cell.setCellValue(testCase.getInitialData().getInitialAction());
                cell.setCellStyle(initialDataStyle(xlsx));
                //проверка результата
                cell = row.createCell(colNum++);
                cell.setCellValue(testCase.getInitialData().getInitialCheck());
                cell.setCellStyle(initialDataStyle(xlsx));
                //цвет столбца BTE
                cell = row.createCell(7);
                cell.setCellStyle(bteActionStyle(xlsx));
            }
            for (TestCaseStepDto step : testCase.getTestCaseStepList()) {
                int colNum = 0;
                //пишем с новой строки
                row = sheet.createRow(rowNum++);
                row.setRowStyle(regularCells(xlsx));
                //номер ТК
                cell = row.createCell(colNum++);
                cell.setCellValue(i + 1);
                cell.setCellStyle(regularCells(xlsx));
                //номер шага
                cell = row.createCell(colNum++);
                cell.setCellValue(step.getStepNumber());
                cell.setCellStyle(regularCells(xlsx));
                //Действие абонента
                cell = row.createCell(colNum++);
                cell.setCellValue(step.getAbonAction().getAbonDict().getAbonDictName());
                cell.setCellStyle(regularCells(xlsx));
                if (step.getAbonAction().getBteAction() != null){
                    //Название Ключевого параметра
                    colNum++;
                    cell = row.createCell(colNum++);
                    cell.setCellValue(step.getAbonAction().getBteAction().getName());
                    cell.setCellStyle(regularCells(xlsx));
                    //Значение Ключевого параметра
                    cell = row.createCell(colNum++);
                    cell.setCellValue(step.getAbonAction().getBteAction().getParamId());
                    cell.setCellStyle(regularCells(xlsx));
                    //Наименование услуги/тарифа/Справка
                    cell = row.createCell(colNum++);
                    cell.setCellValue(step.getAbonAction().getBteAction().getParamValue());
                    cell.setCellStyle(regularCells(xlsx));
                    //Действие BTE
                    cell = row.createCell(colNum++);
                    cell.setCellValue(step.getAbonAction().getAbonDict().getBteDictionary().getBteDictName());
                    cell.setCellStyle(bteActionStyle(xlsx));
                    //Связанные действия проверки
                } else {
                    cell = row.createCell(7);
                    cell.setCellStyle(bteActionStyle(xlsx));
                }
                //коммент
                if (step.getAbonAction().getComment() != null){
                    cell = row.createCell(8);
                    cell.setCellValue(step.getAbonAction().getComment());
                    cell.setCellStyle(regularCells(xlsx));
                }
                for (CheckActionDto checkAction : step.getCheckActions()) {
                    //пишем с новой строки
                    colNum = 0;
                    row = sheet.createRow(rowNum++);
                    row.setRowStyle(regularCells(xlsx));
                    //номер ТК
                    cell = row.createCell(colNum++);
                    cell.setCellValue(i + 1);
                    cell.setCellStyle(regularCells(xlsx));
                    //номер шага
                    cell = row.createCell(colNum++);
                    cell.setCellValue(step.getStepNumber());
                    cell.setCellStyle(regularCells(xlsx));
                    //действие проверки
                    cell = row.createCell(colNum++);
                    cell.setCellStyle(regularCells(xlsx));
                    cell = row.createCell(colNum++);
                    cell.setCellValue(checkAction.getCheckDict().getCheckDictName());
                    cell.setCellStyle(regularCells(xlsx));
                    //Название Ключевого параметра
                    cell = row.createCell(colNum++);
                    cell.setCellValue(checkAction.getBteAction().getName());
                    cell.setCellStyle(regularCells(xlsx));
                    //Значение Ключевого параметра
                    cell = row.createCell(colNum++);
                    cell.setCellValue(checkAction.getBteAction().getParamId());
                    cell.setCellStyle(regularCells(xlsx));
                    //Наименование услуги/тарифа/Справка
                    cell = row.createCell(colNum++);
                    cell.setCellValue(checkAction.getBteAction().getParamValue());
                    cell.setCellStyle(regularCells(xlsx));
                    //Действие BTE
                    cell = row.createCell(colNum++);
                    cell.setCellValue(checkAction.getCheckDict().getBteDictionary().getBteDictName());
                    cell.setCellStyle(bteActionStyle(xlsx));
                    if (checkAction.getComment() != null){
                        cell = row.createCell(8);
                        cell.setCellValue(checkAction.getComment());
                        cell.setCellStyle(regularCells(xlsx));
                    }
                }
            }
        }

        //ширина столбцов в какой то непонятной херне:
        //пиксели умножаем на 36,56254993073225 и берем в int
        sheet.setColumnWidth(0, 915);
        sheet.setColumnWidth(1, 915);
        sheet.setColumnWidth(2, 12797);
        sheet.setColumnWidth(3, 12797);
        sheet.setColumnWidth(4, 6215);
        sheet.setColumnWidth(5, 5484);
        sheet.setColumnWidth(6, 10969);
        sheet.setColumnWidth(7, 12797);
        sheet.setColumnWidth(8, 9323);

        sheet.setZoom(80);


        File file = new File("TestCaseBTE.xlsx");
        try {
            file.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(file);
            xlsx.write(outputStream);
            outputStream.close();
            return file;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private XSSFCellStyle headerStyle(XSSFWorkbook xlsx){
        XSSFCellStyle style = xlsx.createCellStyle();

        //шрифт
        Font font = xlsx.createFont();
        font.setFontName("Tahoma");
        font.setFontHeightInPoints((short)10);
        font.setBold(true);
        style.setFont(font);

        //границы
        style.setBorderBottom(BorderStyle.MEDIUM);
        style.setBorderTop(BorderStyle.MEDIUM);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        //цвет ячейки
        style.setFillForegroundColor(new XSSFColor(new java.awt.Color(146,208,80), new DefaultIndexedColorMap()));
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        return style;
    }

    private XSSFCellStyle initialDataStyle(XSSFWorkbook xlsx){
        XSSFCellStyle style = xlsx.createCellStyle();

        //шрифт
        Font font = xlsx.createFont();
        font.setFontName("Tahoma");
        font.setFontHeightInPoints((short)10);
        style.setFont(font);

        //границы
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        //цвет ячейки
        style.setFillForegroundColor(new XSSFColor(new java.awt.Color(142,169,219), new DefaultIndexedColorMap()));
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

//        style.setVerticalAlignment(VerticalAlignment.CENTER);
        //перенос текста
        style.setWrapText(true);

        return style;
    }

    private XSSFCellStyle bteActionStyle(XSSFWorkbook xlsx){
        XSSFCellStyle style = xlsx.createCellStyle();

        //шрифт
        Font font = xlsx.createFont();
        font.setFontName("Tahoma");
        font.setFontHeightInPoints((short)10);
        style.setFont(font);

        //границы
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);

        //цвет ячейки
        style.setFillForegroundColor(new XSSFColor(new java.awt.Color(244,176,132), new DefaultIndexedColorMap()));
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        //перенос текста
        style.setWrapText(true);

        return style;
    }


    private XSSFCellStyle regularCells(XSSFWorkbook xlsx){
        XSSFCellStyle style = xlsx.createCellStyle();

        //шрифт
        Font font = xlsx.createFont();
        font.setFontName("Tahoma");
        font.setFontHeightInPoints((short)10);
        style.setFont(font);

        //границы
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);

        //цвет ячейки
        style.setFillForegroundColor(new XSSFColor(new java.awt.Color(255,255,255), new DefaultIndexedColorMap()));
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        //перенос текста
        style.setWrapText(true);

        return style;
    }
}
