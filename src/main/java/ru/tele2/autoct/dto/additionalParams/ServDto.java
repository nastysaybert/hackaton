package ru.tele2.autoct.dto.additionalParams;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ServDto {

    private String servId;

    private String servName;

    public String toString(){
        return servName +
                " (serv_id = " +
                servId +
                ")";
    }

}
