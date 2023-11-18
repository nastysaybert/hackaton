package ru.saybert.hackaton.dto.additionalParams;

import lombok.Getter;
import lombok.Setter;
import ru.saybert.hackaton.dto.AdditionalParamDto;

@Setter
@Getter
public class NotifDto extends AdditionalParamDto {

    private String notifId;

    private String notifName;

    public String toString(){
        return "[" + notifId + "] "
                + notifName;
    }

}
