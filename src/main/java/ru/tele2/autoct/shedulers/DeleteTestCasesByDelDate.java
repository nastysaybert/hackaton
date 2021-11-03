package ru.tele2.autoct.shedulers;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ru.tele2.autoct.dto.TestCaseDto;
import ru.tele2.autoct.services.TestCaseService;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

//@EnableScheduling
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
