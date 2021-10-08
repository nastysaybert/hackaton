package ru.tele2.autoct.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class InitialDataDto {

    private Long id;

    private String initialAction;

    private String initialCheck;

}
