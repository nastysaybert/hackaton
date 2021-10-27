package ru.tele2.autoct.services.additionalParams;

import ru.tele2.autoct.dto.additionalParams.BranchDto;

import java.util.List;

public interface BranchService {
    boolean save(BranchDto branchDto);

    void delete(BranchDto branchDto);

    void deleteAll();

    List<BranchDto> getAll();

    BranchDto getById(String id);
}
