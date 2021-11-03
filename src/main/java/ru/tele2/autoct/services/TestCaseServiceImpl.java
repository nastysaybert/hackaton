package ru.tele2.autoct.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tele2.autoct.dto.*;
import ru.tele2.autoct.jpa.entity.AbonActionEntity;
import ru.tele2.autoct.jpa.entity.TestCaseEntity;
import ru.tele2.autoct.jpa.repository.*;
import ru.tele2.autoct.mappers.TestCaseMapper;
import ru.tele2.autoct.views.components.TestCaseForm;
import ru.tele2.autoct.views.components.TestCaseStepForm;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@AllArgsConstructor
public class TestCaseServiceImpl implements TestCaseService{

    private final TestCaseRepository testCaseRepository;
    private final TestCaseMapper testCaseMapper;
    private final TestCaseStepRepository testCaseStepRepository;
    private final InitialDataRepository initialDataRepository;
    private final AbonActionRepository abonActionRepository;
    private final CheckActionRepository checkActionRepository;
    private final BTEActionRepository bteActionRepository;


    public TestCaseDto getTestCaseDtoFromForm(TestCaseForm testCaseForm){
        TestCaseDto testCaseDto = new TestCaseDto();
        if (testCaseForm.getTestCaseId() >= 0){
            testCaseDto.setId(testCaseForm.getTestCaseId());
        }
        testCaseDto.setName(testCaseForm.getHeader().getValue());
        testCaseDto.setTemplate(false);
        if (testCaseForm.getInitialDataForm() != null){
            testCaseDto.setInitialData(testCaseForm.getInitialDataForm().getInitialDataDto());
        }
        AtomicInteger i = new AtomicInteger(1);
        List<TestCaseStepDto> steps = new ArrayList<>();
        testCaseForm.getStepForms().forEach((id, stepForm) -> {
            TestCaseStepDto testCaseStepDto = stepForm.getTestCaseStepDto();
            testCaseStepDto.setStepNumber(Long.valueOf(i.get()));
            steps.add(testCaseStepDto);
            i.incrementAndGet();
        });
        testCaseDto.setTestCaseStepList(steps);
        return testCaseDto;
    }

    public boolean save(TestCaseDto testCaseDto){

        if ((testCaseRepository.getByName(testCaseDto.getName()) != null) && (testCaseDto.getId() == null)){
            return false;
        } else {
            TestCaseEntity entity = testCaseMapper.convert(testCaseDto);
            testCaseRepository.save(entity);
            if (entity.getInitialData() != null){
                entity.getInitialData().setTestCase(entity);
                initialDataRepository.save(entity.getInitialData());
            }
            entity.getTestCaseStepList().forEach(testCaseStepEntity -> {
                testCaseStepEntity.setTestCase(entity);
                testCaseStepEntity.getAbonAction().setTestCaseStep(testCaseStepEntity);
                if (testCaseStepEntity.getAbonAction().getBteAction() != null){
                    testCaseStepEntity.getAbonAction().getBteAction().setAbonAction(testCaseStepEntity.getAbonAction());
                }
                testCaseStepRepository.save(testCaseStepEntity);
                abonActionRepository.save(testCaseStepEntity.getAbonAction());
                if (testCaseStepEntity.getAbonAction().getBteAction() != null){
                    bteActionRepository.save(testCaseStepEntity.getAbonAction().getBteAction());
                }
                testCaseStepEntity.getCheckActions().forEach(checkActionEntity -> {
                    checkActionEntity.setTestCaseStep(testCaseStepEntity);
                    checkActionEntity.getBteAction().setCheckAction(checkActionEntity);
                    checkActionRepository.save(checkActionEntity);
                    bteActionRepository.save(checkActionEntity.getBteAction());
                });
            });
            return true;
        }
    }

    public void delete (TestCaseDto testCaseDto){
        TestCaseEntity entity = testCaseMapper.convert(testCaseDto);
        testCaseRepository.delete(entity);
    }

    public void setDelDate (TestCaseDto testCaseDto){
        TestCaseEntity entity = testCaseMapper.convert(testCaseDto);
        entity.setDelDate(LocalDateTime.now(ZoneId.of("Europe/Moscow")));
        testCaseRepository.save(entity);
    }


    @Transactional
    public TestCaseDto getById(Long id){
        return testCaseMapper.convert(testCaseRepository.getById(id));
    }

    @Transactional
    public List<TestCaseDto> getAll(){
        List<TestCaseDto> result = new ArrayList<>();
        List<TestCaseEntity> testCaseEntityList = testCaseRepository.findAll();
        for(TestCaseEntity testCaseEntity:testCaseEntityList){
            result.add(testCaseMapper.convert(testCaseEntity));
        }
        return result;
    }

    @Transactional
    public List<TestCaseDto> getAllTemplates() {
        List<TestCaseDto> result = new ArrayList<>();
        List<TestCaseEntity> testCaseEntityList = testCaseRepository.getAllByTemplateIsTrueAndDelDateIsNull();
        for(TestCaseEntity testCaseEntity:testCaseEntityList){
            result.add(testCaseMapper.convert(testCaseEntity));
        }
        return result;
    }

    @Transactional
    public List<TestCaseDto> getAllTestCases() {
        List<TestCaseDto> result = new ArrayList<>();
        List<TestCaseEntity> testCaseEntityList = testCaseRepository.getAllByTemplateIsFalseAndDelDateIsNull();
        for(TestCaseEntity testCaseEntity:testCaseEntityList){
            result.add(testCaseMapper.convert(testCaseEntity));
        }
        return result;
    }

    @Transactional
    public List<TestCaseDto> getAllDeleted() {
        List<TestCaseDto> result = new ArrayList<>();
        List<TestCaseEntity> testCaseEntityList = testCaseRepository.getAllByDelDateIsNotNull();
        for(TestCaseEntity testCaseEntity:testCaseEntityList){
            result.add(testCaseMapper.convert(testCaseEntity));
        }
        return result;
    }
}
