package ru.tele2.autoct.dto.additionalParams;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TrplDto {

    private String trplId;

    private String trplName;

    public String toString(){
        return trplName +
                " (trpl_id = " +
                trplId +
                ")";
    }

}
