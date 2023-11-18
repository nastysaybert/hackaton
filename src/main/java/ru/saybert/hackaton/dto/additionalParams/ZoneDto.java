package ru.saybert.hackaton.dto.additionalParams;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ZoneDto {

    private String zoneId;

    private String zoneName;

    public String toString(){
        return zoneName
                + " (ZONE_ID = "
                + zoneId
                + ")";
    }
}
