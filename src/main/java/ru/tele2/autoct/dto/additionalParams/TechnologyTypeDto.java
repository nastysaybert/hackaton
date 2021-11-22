package ru.tele2.autoct.dto.additionalParams;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TechnologyTypeDto {

    private String technologyTypeId;

    private String technologyTypeName;

    public String toString(){
        return technologyTypeName;
    }
}
