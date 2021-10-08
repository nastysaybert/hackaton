package ru.tele2.autoct.mappers.additionalParams;

import org.mapstruct.Mapper;
import ru.tele2.autoct.dto.additionalParams.BranchDto;
import ru.tele2.autoct.jpa.entity.additionalParams.BranchEntity;

@Mapper(componentModel = "spring")
public interface BranchMapper {

    BranchDto convert(BranchEntity branch);

    BranchEntity convert(BranchDto branch);


}
