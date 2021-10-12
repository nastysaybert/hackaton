package ru.tele2.autoct.dto.additionalParams;

import lombok.Getter;
import lombok.Setter;
import ru.tele2.autoct.dto.AdditionalParamDto;

@Setter
@Getter
public class NotifDto extends AdditionalParamDto {

    private String notifId;

    private String notifName;

}
