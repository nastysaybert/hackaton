package ru.tele2.autoct.shedulers;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ru.tele2.autoct.dto.additionalParams.BranchDto;
import ru.tele2.autoct.dto.additionalParams.ServDto;
import ru.tele2.autoct.dto.additionalParams.TrplDto;
import ru.tele2.autoct.services.additionalParams.BranchService;
import ru.tele2.autoct.services.additionalParams.ServService;
import ru.tele2.autoct.services.additionalParams.TrplService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Iterator;

//@EnableScheduling
@Configuration
@EnableConfigurationProperties
@RequiredArgsConstructor
public class UpdatingDicts {

    private final TrplService trplService;
    private final ServService servService;
    private final BranchService branchService;
    @Value("${external.filepath.projects}")
    String filepath;

    @Scheduled(fixedRate = 86400000)
    public void updateTrplFixedRate(){
        try {
            FileInputStream file = new FileInputStream(new File(filepath));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet("TRPL");
            if (sheet.getLastRowNum() > 2){
                //очищаем таблицу и добавляем данные с нуля для меньшего числа итераций
                trplService.deleteAll();
                Iterator<Row> rowIterator = sheet.iterator();
                Row row = rowIterator.next();
                TrplDto trplDto = new TrplDto();
                while (rowIterator.hasNext()){
                    row = rowIterator.next();
                    trplDto.setTrplId(row.getCell(0).getStringCellValue());
                    trplDto.setTrplName(row.getCell(1).getStringCellValue());
                    trplService.save(trplDto);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(fixedRate = 86400000)
    public void updateServFixedRate(){
        try {
            FileInputStream file = new FileInputStream(new File(filepath));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet("SERV");
            if (sheet.getLastRowNum() > 2){
                servService.deleteAll();
                Iterator<Row> rowIterator = sheet.iterator();
                Row row = rowIterator.next();
                ServDto servDto = new ServDto();
                while (rowIterator.hasNext()){
                    row = rowIterator.next();
                    servDto.setServId(rounding(row.getCell(0).getNumericCellValue()));
                    servDto.setServName(row.getCell(1).getStringCellValue());
                    servService.save(servDto);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(fixedRate = 86400000)
    public void updateBranchFixedRate(){
        try {
            FileInputStream file = new FileInputStream(new File(filepath));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet("BRANCH");
            if (sheet.getLastRowNum() > 2){
                branchService.deleteAll();
                Iterator<Row> rowIterator = sheet.iterator();
                Row row = rowIterator.next();
                BranchDto branchDto = new BranchDto();
                while (rowIterator.hasNext()){
                    row = rowIterator.next();
                    branchDto.setBranchId(rounding(row.getCell(0).getNumericCellValue()));
                    branchDto.setBranchName(row.getCell(1).getStringCellValue());
                    branchService.save(branchDto);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String rounding (double doubleValue){
        DecimalFormat df = new DecimalFormat("#");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(doubleValue);
    }
}
