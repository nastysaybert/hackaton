package ru.saybert.hackaton.shedulers;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import ru.saybert.hackaton.dto.TestCaseDto;
import ru.saybert.hackaton.services.TestCaseService;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

//@EnableScheduling находится в файле UpdatingDicts
@Configuration
@EnableConfigurationProperties
@RequiredArgsConstructor
public class DeleteTestCasesByDelDate {

    private final TestCaseService testCaseService;

    @Scheduled(fixedRate = 86400000)
    public void DeleteTestCasesByDelDate(){
        List<TestCaseDto> allByDelDateIsNotNull = testCaseService.getAllDeleted();
        allByDelDateIsNotNull.forEach(testCaseDto -> {
            if (LocalDateTime.now(ZoneId.of("Europe/Moscow")).minusDays(2).isAfter(testCaseDto.getDelDate())){
                testCaseService.delete(testCaseDto);
            }
        });
    }
}
