package ru.tele2.autoct.dto.additionalParams;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SummDto{

    private String amount;

    private String unit = "руб.";
}
