package ru.tele2.autoct.dto.additionalParams;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ActivationMethodDto {

    private String method;

    private String description;

    public String toString(){
        return description
                + " ("
                + method
                + ")";
    }

}
