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
        testCaseDto.setName(testCaseForm.getHeader().getValue());
        testCaseDto.setTemplate(false);
        if (testCaseForm.getInitialDataForm() != null){
            testCaseDto.setInitialData(testCaseForm.getInitialDataForm().getInitialDataDto());
        }
        AtomicInteger i = new AtomicInteger(1);
        TreeMap<Integer, TestCaseStepForm> stepForms =  testCaseForm.getStepForms();
        List<TestCaseStepDto> testCaseStepDtoList = new ArrayList<>();
        stepForms.forEach((id, stepForm) -> {
            TestCaseStepDto testCaseStepDto = new TestCaseStepDto();
            BTEActionDto bteActionDto = new BTEActionDto();
            AbonActionDto abonActionDto = new AbonActionDto();
            abonActionDto.setAbonDict(stepForm.getAbonAction().getFirst().getAbonDictionaryDto());
            if (!stepForm.getAbonAction().getFirst().getComment().isEmpty()){
                abonActionDto.setComment(stepForm.getAbonAction().getFirst().getComment());
            }
            if (abonActionDto.getAbonDict().getBteDictionary() != null){
                bteActionDto.setName(abonActionDto.getAbonDict().getBteDictionary().getParamType().toString());
                bteActionDto.setParamId(stepForm.getAbonAction().getSecond().getAdditionalParamDto().getParamId());
                bteActionDto.setParamValue(stepForm.getAbonAction().getSecond().getAdditionalParamDto().getParamValue());
                abonActionDto.setBteAction(bteActionDto);
            }
            testCaseStepDto.setAbonAction(abonActionDto);
            List<CheckActionDto> checkActions = new ArrayList<>();
            stepForm.getCheckActions().forEach( (checkId, pairCheckAndParam) ->{
                CheckActionDto checkActionDto = new CheckActionDto();
                checkActionDto.setCheckDict(pairCheckAndParam.getFirst().getCheckDictionaryDto());
                if (!pairCheckAndParam.getFirst().getComment().isEmpty()){
                    checkActionDto.setComment(pairCheckAndParam.getFirst().getComment());
                }
                BTEActionDto bteActionDtoForCheck = new BTEActionDto();
                bteActionDtoForCheck.setName(checkActionDto.getCheckDict().getBteDictionary().getParamType().toString());
                bteActionDtoForCheck.setParamId(pairCheckAndParam.getSecond().getAdditionalParamDto().getParamId());
                bteActionDtoForCheck.setParamValue(pairCheckAndParam.getSecond().getAdditionalParamDto().getParamValue());
                checkActionDto.setBteAction(bteActionDtoForCheck);
                checkActions.add(checkActionDto);
            });
            testCaseStepDto.setCheckActions(checkActions);
            testCaseStepDto.setStepNumber(Long.valueOf(i.get()));
            testCaseStepDtoList.add(testCaseStepDto);
            i.incrementAndGet();
        });
        testCaseDto.setTestCaseStepList(testCaseStepDtoList);
        return testCaseDto;
    }

    public boolean save(TestCaseDto testCaseDto){
        if (testCaseRepository.getByName(testCaseDto.getName()) == null) {
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
        } else return false;
    }

    public void delete (TestCaseDto testCaseDto){
        TestCaseEntity entity = testCaseMapper.convert(testCaseDto);
        testCaseRepository.delete(entity);
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
}
