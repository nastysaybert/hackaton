package ru.saybert.hackaton.dto.additionalParams;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DurationDto {

    private String duration;

    private String unit = "сек.";

}
