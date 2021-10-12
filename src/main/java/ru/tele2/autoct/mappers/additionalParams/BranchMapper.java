package ru.tele2.autoct.mappers.additionalParams;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.tele2.autoct.dto.AdditionalParamDto;
import ru.tele2.autoct.dto.additionalParams.BranchDto;
import ru.tele2.autoct.jpa.entity.additionalParams.BranchEntity;

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
