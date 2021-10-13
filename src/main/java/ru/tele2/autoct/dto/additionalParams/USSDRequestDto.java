package ru.tele2.autoct.dto.additionalParams;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class USSDRequestDto {

    private String ussd;

    private String description;
}
