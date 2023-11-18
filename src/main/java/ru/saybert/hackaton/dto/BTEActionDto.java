package ru.saybert.hackaton.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.saybert.hackaton.enums.ParamType;


@Setter
@Getter
@NoArgsConstructor
public class BTEActionDto {

    private Long id;

//    private String name;

    private ParamType paramType;

    private String paramId;

    private String paramValue;
}
