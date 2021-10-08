package ru.tele2.autoct.dto.additionalParams;


import ru.tele2.autoct.dto.AdditionalParamDto;
import ru.tele2.autoct.enums.ParamType;


public class TrplDto extends AdditionalParamDto {

    private Long trplId;

    private String trplName;

    private final ParamType paramType = ParamType.TRPL;

    public Long getParamId(){
        return this.trplId;
    }

    public ParamType getParamType(){
        return ParamType.TRPL;
    };

    public String getParamValue(){
        return this.trplName;
    };

    public void setParamId(Long paramId){
        this.trplId = paramId;
    };

    public void setParamValue(String paramValue){
        this.trplName = paramValue;
    };

    public String toString(){
        return this.trplName
                + " (trpl_id = "
                + this.trplId
                + ")";
    }



}
