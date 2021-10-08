package ru.tele2.autoct.dto.additionalParams;

import ru.tele2.autoct.dto.AdditionalParamDto;
import ru.tele2.autoct.enums.ParamType;

public class ServDto extends AdditionalParamDto {

    private Long servId;

    private String servName;

    private final ParamType paramType = ParamType.SERV;

    public Long getParamId(){
        return this.servId;
    }

    public ParamType getParamType(){
        return ParamType.SERV;
    };

    public String getParamValue(){
        return this.servName;
    };

    public void setParamId(Long paramId){
        this.servId = paramId;
    };

    public void setParamValue(String paramValue){
        this.servName = paramValue;
    };

    public String toString(){
        return this.servName
                + " (serv_id = "
                + this.servId
                + ")";
    }

}
