package ru.tele2.autoct.dto.additionalParams;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TrplDto {

    private String trplId;

    private String trplName;

    public String toString(){
        StringBuilder trplIdBuilder = new StringBuilder(trplId);
        trplIdBuilder.delete(0,3);
        return trplName +
                " (trpl_id = " +
                trplIdBuilder +
                ")";
    }

}
