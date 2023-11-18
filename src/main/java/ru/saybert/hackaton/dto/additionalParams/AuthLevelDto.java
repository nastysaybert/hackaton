package ru.saybert.hackaton.dto.additionalParams;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthLevelDto {

    private String authLevelId;

    private String authLevelName;

    public String toString(){
        return authLevelId +
                " (" +
                authLevelName +
                ")";
    }
}
