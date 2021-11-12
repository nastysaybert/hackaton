package ru.tele2.autoct.views.components.additionalParams;

import com.vaadin.flow.component.combobox.ComboBox;
import ru.tele2.autoct.dto.AdditionalParamDto;
import ru.tele2.autoct.dto.additionalParams.ZoneDto;
import ru.tele2.autoct.services.additionalParams.BranchService;
import ru.tele2.autoct.services.additionalParams.ZoneService;

import java.util.Collections;
import java.util.List;

public class ZoneForm extends ComboBox<ZoneDto> {

    public ZoneForm(ZoneService zoneService){
        this.setWidthFull();
        this.setClearButtonVisible(true);
        this.setItemLabelGenerator(ZoneDto::toString);
//        this.setLabel("Выберите зону звонка");
        this.setPlaceholder("Выберите зону звонка");
        List<ZoneDto> sortedZones = zoneService.getAll();
        Collections.sort(sortedZones, (o1, o2) -> {
            if (Integer.parseInt(o1.getZoneId()) > Integer.parseInt(o2.getZoneId())) {
                return 1;
            } else {
                if (Integer.parseInt(o1.getZoneId()) < Integer.parseInt(o2.getZoneId())) {
                    return -1;
                } else return 0;
            }
        });
        this.setItems(sortedZones);
    }

    public AdditionalParamDto getParam(){
        AdditionalParamDto result = new AdditionalParamDto();
        if (this.getValue() != null){
            result.setParamId(this.getValue().getZoneId());
            result.setParamValue(this.getValue().getZoneName());
        } else return null;
        return result;
    }

    public void setParam(AdditionalParamDto additionalParamDto, ZoneService zoneService){
        if (additionalParamDto.getParamId() != null){
            this.setValue(zoneService.getById(additionalParamDto.getParamId()));
        }
    }
}
