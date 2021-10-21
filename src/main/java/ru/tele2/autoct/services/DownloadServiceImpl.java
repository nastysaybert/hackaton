package ru.tele2.autoct.services;

import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import ru.tele2.autoct.dto.CheckActionDto;
import ru.tele2.autoct.dto.TestCaseDto;
import ru.tele2.autoct.dto.TestCaseStepDto;
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
            cell = row.createCell(0);
            cell.setCellValue(testCase.getName());
            if (testCase.getInitialData() != null) {
                int colNum = 0;
                //пишем с новой строки
                row = sheet.createRow(rowNum++);
                //номер ТК
                cell = row.createCell(colNum++);
                cell.setCellValue(i+1);
                //номер шага
                cell = row.createCell(colNum++);
                cell.setCellValue(0);
                //действие
                cell = row.createCell(colNum++);
                cell.setCellValue(testCase.getInitialData().getInitialAction());
                //проверка результата
                cell = row.createCell(colNum++);
                cell.setCellValue(testCase.getInitialData().getInitialCheck());
            }
            for (TestCaseStepDto step : testCase.getTestCaseStepList()) {
                int colNum = 0;
                //пишем с новой строки
                row = sheet.createRow(rowNum++);
                //номер ТК
                cell = row.createCell(colNum++);
                cell.setCellValue(i + 1);
                //номер шага
                cell = row.createCell(colNum++);
                cell.setCellValue(step.getStepNumber());
                //Действие абонента
                cell = row.createCell(colNum++);
                cell.setCellValue(step.getAbonAction().getAbonDict().getAbonDictName());
                if (step.getAbonAction().getBteAction() != null){
                    //пропускаем столбец
//                    colNum++;
                    //Название Ключевого параметра
                    cell = row.createCell(colNum++);
                    cell.setCellValue(step.getAbonAction().getBteAction().getName());
                    //Значение Ключевого параметра
                    cell = row.createCell(colNum++);
                    cell.setCellValue(step.getAbonAction().getBteAction().getParamId());
                    //Наименование услуги/тарифа/Справка
                    cell = row.createCell(colNum++);
                    cell.setCellValue(step.getAbonAction().getBteAction().getParamValue());
                    //Действие BTE
                    cell = row.createCell(colNum++);
                    cell.setCellValue(step.getAbonAction().getAbonDict().getBteDictionary().getBteDictName());
                    //Связанные действия проверки
                }
                for (CheckActionDto checkAction : step.getCheckActions()) {
                    //пишем с новой строки
                    colNum = 0;
                    row = sheet.createRow(rowNum++);
                    //номер ТК
                    cell = row.createCell(colNum++);
                    cell.setCellValue(i + 1);
                    //номер шага
                    cell = row.createCell(colNum++);
                    cell.setCellValue(step.getStepNumber());
                    //номер шага
                    cell = row.createCell(colNum++);
                    cell = row.createCell(colNum++);
                    cell.setCellValue(checkAction.getCheckDict().getCheckDictName());
                    //Название Ключевого параметра
                    cell = row.createCell(colNum++);
                    cell.setCellValue(checkAction.getBteAction().getName());
                    //Значение Ключевого параметра
                    cell = row.createCell(colNum++);
                    cell.setCellValue(checkAction.getBteAction().getParamId());
                    //Наименование услуги/тарифа/Справка
                    cell = row.createCell(colNum++);
                    cell.setCellValue(checkAction.getBteAction().getParamValue());
                    //Действие BTE
                    cell = row.createCell(colNum++);
                    cell.setCellValue(checkAction.getCheckDict().getBteDictionary().getBteDictName());
                }
//                row = sheet.createRow(rowNum++);
            }
            row = sheet.createRow(rowNum++);
        }

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
}
