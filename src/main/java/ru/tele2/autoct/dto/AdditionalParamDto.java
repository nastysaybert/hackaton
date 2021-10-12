package ru.tele2.autoct.dto;

import lombok.Getter;
import lombok.Setter;
import ru.tele2.autoct.enums.ParamType;
import ru.tele2.autoct.jpa.interfaces.AdditionalParameter;

@Getter
@Setter
public class AdditionalParamDto{

    private String paramId;

    private String paramValue;
}
