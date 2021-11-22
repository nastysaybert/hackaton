package ru.tele2.autoct.dto.additionalParams;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientTypeDto {

    private String clientTypeId;

    private String clientTypeName;

    public String toString(){
        return clientTypeName +
                " (id = " +
                clientTypeId +
                ")";
    }
}
