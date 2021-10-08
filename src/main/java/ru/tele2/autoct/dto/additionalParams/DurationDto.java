package ru.tele2.autoct.dto.additionalParams;

import ru.tele2.autoct.dto.AdditionalParamDto;
import ru.tele2.autoct.enums.ParamType;

public class DurationDto extends AdditionalParamDto {

    private final ParamType paramType = ParamType.DURATION;

    private Long duration;

    private final String unit = "сек.";

    public Long getParamId(){
        return this.duration;
    }

    public ParamType getParamType(){
        return ParamType.DURATION;
    };

    public String getParamValue(){
        return this.unit;
    };

    public void setParamId(Long paramId){
        this.duration = paramId;
    };

}
