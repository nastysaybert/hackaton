package ru.tele2.autoct.jpa.interfaces;

import ru.tele2.autoct.enums.ParamType;

public abstract class AdditionalParameter {

    public abstract Long getParamId();

    public abstract ParamType getParamType();

    public abstract String getParamValue();

    public abstract void setParamType(ParamType paramType);

    public abstract void setParamId(Long paramId);

    public abstract void setParamValue(String paramValue);

    public abstract String toString();
}