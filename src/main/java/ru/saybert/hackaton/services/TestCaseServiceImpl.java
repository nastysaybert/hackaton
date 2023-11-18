package ru.saybert.hackaton.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.saybert.hackaton.dto.TestCaseDto;
import ru.saybert.hackaton.dto.TestCaseStepDto;
import ru.saybert.hackaton.jpa.entity.BTEActionEntity;
import ru.saybert.hackaton.jpa.entity.TestCaseEntity;
import ru.saybert.hackaton.jpa.repository.*;
import ru.saybert.hackaton.mappers.dictionaries.ProjectMapper;
import ru.saybert.hackaton.dto.dictionaries.ProjectDto;
import ru.saybert.hackaton.mappers.TestCaseMapper;

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
    private final ProjectMapper projectMapper;


    public TestCaseDto getTestCaseDtoFromForm(TestCaseForm testCaseForm){
        TestCaseDto testCaseDto = new TestCaseDto();
        if (testCaseForm.getTestCaseId() >= 0){
            testCaseDto.setId(testCaseForm.getTestCaseId());
            testCaseDto.setAuthor(testCaseRepository.getById(testCaseDto.getId()).getAuthor());
        }
        testCaseDto.setProject(testCaseForm.getProjectForm().getValue());
        testCaseDto.setName(testCaseForm.getHeader().getValue());
        testCaseDto.setTemplate(testCaseForm.isTemplate());
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
        if ((testCaseRepository.getByNameAndDelDateIsNull(testCaseDto.getName()) != null) && (testCaseDto.getId() == null)){
            return false;
        } else {
            TestCaseEntity entity = testCaseMapper.convert(testCaseDto);
            if (entity.getAuthor() == null){
                entity.setAuthor(SecurityContextHolder.getContext().getAuthentication().getName());
            }
            entity.setRedactor(SecurityContextHolder.getContext().getAuthentication().getName());
            testCaseRepository.save(entity);
            if (entity.getInitialData() != null){
                entity.getInitialData().setTestCase(entity);
                initialDataRepository.save(entity.getInitialData());
            }
            entity.getTestCaseStepList().forEach(testCaseStepEntity -> {
                testCaseStepEntity.setTestCase(entity);
                testCaseStepEntity.getAbonAction().setTestCaseStep(testCaseStepEntity);
                if (testCaseStepEntity.getAbonAction().getBteActions() != null){
                    for (BTEActionEntity bteAction : testCaseStepEntity.getAbonAction().getBteActions()) {
                        bteAction.setAbonAction(testCaseStepEntity.getAbonAction());
                    }
                }
                testCaseStepRepository.save(testCaseStepEntity);
                abonActionRepository.save(testCaseStepEntity.getAbonAction());
                if (testCaseStepEntity.getAbonAction().getBteActions() != null){
                    for (BTEActionEntity bteAction : testCaseStepEntity.getAbonAction().getBteActions()) {
                        bteActionRepository.save(bteAction);
                    }
                }
                testCaseStepEntity.getCheckActions().forEach(checkActionEntity -> {
                    checkActionEntity.setTestCaseStep(testCaseStepEntity);
                    for (BTEActionEntity bteAction : checkActionEntity.getBteActions()) {
                        bteAction.setCheckAction(checkActionEntity);
                    }
                    checkActionRepository.save(checkActionEntity);
                    for (BTEActionEntity bteAction : checkActionEntity.getBteActions()) {
                        bteActionRepository.save(bteAction);
                    }
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

    @Transactional
    public List<TestCaseDto> getAllByProject(ProjectDto project) {
        List<TestCaseDto> result = new ArrayList<>();
        List<TestCaseEntity> testCaseEntityList = testCaseRepository.getAllByProject(projectMapper.convert(project));
        for(TestCaseEntity testCaseEntity:testCaseEntityList){
            result.add(testCaseMapper.convert(testCaseEntity));
        }
        return result;
    }
}
