package ru.tele2.autoct.dto.additionalParams;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CountDto {

    private String count;

    private String unit = "шт.";

}
