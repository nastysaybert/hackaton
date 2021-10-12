package ru.tele2.autoct.dto.additionalParams;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DurationDto {

    private String duration;

    private String unit = "сек.";

}
