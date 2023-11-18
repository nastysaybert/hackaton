package ru.saybert.hackaton.mappers.additionalParams;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.saybert.hackaton.dto.AdditionalParamDto;
import ru.saybert.hackaton.dto.additionalParams.BranchDto;
import ru.saybert.hackaton.jpa.entity.additionalParams.BranchEntity;

@Mapper(componentModel = "spring")
public interface BranchMapper {

    BranchDto convert(BranchEntity branch);

    BranchEntity convert(BranchDto branch);

    @Mappings({
            @Mapping(target="branchId", source="param.paramId"),
            @Mapping(target="branchName", source="param.paramValue")
    })
    BranchDto convert(AdditionalParamDto param);

}
