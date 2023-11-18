package ru.saybert.hackaton.dto.additionalParams;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SummDto{

    private String amount;

    private String unit = "руб.";
}
