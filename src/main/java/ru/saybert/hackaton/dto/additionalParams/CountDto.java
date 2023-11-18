package ru.saybert.hackaton.dto.additionalParams;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CountDto {

    private String count;

    private String unit = "шт.";

}
