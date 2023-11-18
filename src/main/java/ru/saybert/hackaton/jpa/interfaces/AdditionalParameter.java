package ru.saybert.hackaton.jpa.interfaces;

import ru.saybert.hackaton.enums.ParamType;

public abstract class AdditionalParameter {

    public abstract String getParamId();

    public abstract ParamType getParamType();

    public abstract String getParamValue();

    public abstract void setParamType(ParamType paramType);

    public abstract void setParamId(String paramId);

    public abstract void setParamValue(String paramValue);

    public abstract String toString();
}
