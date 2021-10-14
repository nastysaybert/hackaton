package ru.tele2.autoct.services.additionalParams;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tele2.autoct.dto.additionalParams.BranchDto;
import ru.tele2.autoct.jpa.entity.additionalParams.BranchEntity;
import ru.tele2.autoct.jpa.repository.additionalParams.BranchRepository;
import ru.tele2.autoct.mappers.additionalParams.BranchMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BranchServiceImpl implements BranchService{

    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;

    public boolean save(BranchDto branchDto) {
        BranchEntity entity = branchMapper.convert(branchDto);
        branchRepository.save(entity);
        return true;
    }

    public void delete(BranchDto branchDto) {
        BranchEntity entity = branchMapper.convert(branchDto);
        branchRepository.delete(entity);
    }

    public void deleteAll (){
        branchRepository.deleteAll();
    }

    public List<BranchDto> getAll(){
        List<BranchDto> branchDtoList = new ArrayList<>();
        List<BranchEntity> branchEntityList = branchRepository.findAll();
        for (BranchEntity branchEntity: branchEntityList) {
            branchDtoList.add(branchMapper.convert(branchEntity));
        }
        return branchDtoList;
    }
}
