package ru.saybert.hackaton.services.additionalParams;

import ru.saybert.hackaton.dto.additionalParams.BranchDto;

import java.util.List;

public interface BranchService {
    boolean save(BranchDto branchDto);

    void delete(BranchDto branchDto);

    void deleteAll();

    List<BranchDto> getAll();

    BranchDto getById(String id);
}
