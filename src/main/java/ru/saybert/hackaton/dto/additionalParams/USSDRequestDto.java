package ru.saybert.hackaton.dto.additionalParams;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class USSDRequestDto {

    private String ussd;

    private String description;
}
