package ru.tele2.autoct.dto;

import ru.tele2.autoct.enums.ParamType;
import ru.tele2.autoct.jpa.interfaces.AdditionalParameter;

public class AdditionalParamDto extends AdditionalParameter {

    private ParamType paramType;

    private Long paramId;

    private String paramValue;


    @Override
    public Long getParamId() {
        return this.paramId;
    }

    @Override
    public ParamType getParamType() {
        return this.paramType;
    }

    @Override
    public String getParamValue() {
        return this.paramValue;
    }

    public void setParamType(ParamType paramType){
        this.paramType = paramType;
    };

    @Override
    public void setParamId(Long paramId) {
        this.paramId = paramId;
    }

    @Override
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    @Override
    public String toString() {
        return this.paramValue +
                " (id = " + this.paramId + ")";
    }
}
