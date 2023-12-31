package ru.saybert.hackaton.mappers.additionalParams;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.saybert.hackaton.dto.AdditionalParamDto;
import ru.saybert.hackaton.dto.additionalParams.*;

@Mapper
public interface AdditionalParamsMapper {

    @Mappings({
            @Mapping(target="paramId", source="param.trplId"),
            @Mapping(target="paramValue", source="param.trplName")
    })
    AdditionalParamDto convert(TrplDto param);

    @Mappings({
            @Mapping(target="paramId", source="param.servId"),
            @Mapping(target="paramValue", source="param.servName")
    })
    AdditionalParamDto convert(ServDto param);

    @Mappings({
            @Mapping(target="paramId", source="param.branchId"),
            @Mapping(target="paramValue", source="param.branchName")
    })
    AdditionalParamDto convert(BranchDto param);

    @Mappings({
            @Mapping(target="paramId", source="param.notifId"),
            @Mapping(target="paramValue", source="param.notifName")
    })
    AdditionalParamDto convert(NotifDto param);

    @Mappings({
            @Mapping(target="paramId", source="param.authLevelId"),
            @Mapping(target="paramValue", source="param.authLevelName")
    })
    AdditionalParamDto convert(AuthLevelDto param);

    @Mappings({
            @Mapping(target="paramId", source="param.duration"),
            @Mapping(target="paramValue", source="param.unit")
    })
    AdditionalParamDto convert(DurationDto param);

    @Mappings({
            @Mapping(target="paramId", source="param.amount"),
            @Mapping(target="paramValue", source="param.unit")
    })
    AdditionalParamDto convert(SummDto param);

    @Mappings({
            @Mapping(target="paramId", source="param.ussd"),
            @Mapping(target="paramValue", source="param.description")
    })
    AdditionalParamDto convert(USSDRequestDto param);

    @Mappings({
            @Mapping(target="paramId", source="param.clntId"),
            @Mapping(target="paramValue", source="param.unit")
    })
    AdditionalParamDto convert(ClntIdDto param);

    @Mappings({
            @Mapping(target="paramId", source="param.zoneId"),
            @Mapping(target="paramValue", source="param.zoneName")
    })
    AdditionalParamDto convert(ZoneDto param);

    @Mappings({
            @Mapping(target="paramId", source="param.method"),
            @Mapping(target="paramValue", source="param.description")
    })
    AdditionalParamDto convert(ActivationMethodDto param);

    @Mappings({
            @Mapping(target="paramId", source="param.technologyTypeId"),
            @Mapping(target="paramValue", source="param.technologyTypeName")
    })
    AdditionalParamDto convert(TechnologyTypeDto param);

    @Mappings({
            @Mapping(target="paramId", source="param.clientTypeId"),
            @Mapping(target="paramValue", source="param.clientTypeName")
    })
    AdditionalParamDto convert(ClientTypeDto param);

    @Mappings({
            @Mapping(target="paramId", source="param.count"),
            @Mapping(target="paramValue", source="param.unit")
    })
    AdditionalParamDto convert(CountDto param);

    @Mappings({
            @Mapping(target="paramId", source="param.count"),
            @Mapping(target="paramValue", source="param.unit")
    })
    AdditionalParamDto convert(TextDto param);
}
