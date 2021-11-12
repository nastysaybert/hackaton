package ru.tele2.autoct.dto.additionalParams;

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
