package ru.saybert.hackaton.dto.additionalParams;

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
