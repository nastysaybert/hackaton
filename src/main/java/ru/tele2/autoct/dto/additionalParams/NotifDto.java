package ru.tele2.autoct.dto.additionalParams;

import ru.tele2.autoct.dto.AdditionalParamDto;
import ru.tele2.autoct.enums.ParamType;

public class NotifDto extends AdditionalParamDto {

    private Long notifId;

    private String notifName;

    private final ParamType paramType = ParamType.NOTIF;

    public Long getParamId(){
        return this.notifId;
    }

    public ParamType getParamType(){
        return ParamType.NOTIF;
    };

    public String getParamValue(){
        return this.notifName;
    };

    public void setParamId(Long paramId){
        this.notifId = paramId;
    };

    public void setParamValue(String paramValue){
        this.notifName = paramValue;
    };

    public String toString(){
        return "[-"
                + this.notifId
                + "] "
                + this.notifName;
    }


}
