package ru.tele2.autoct.views.components.additionalParams;

import com.vaadin.flow.component.combobox.ComboBox;
import ru.tele2.autoct.dto.AdditionalParamDto;
import ru.tele2.autoct.dto.additionalParams.ActivationMethodDto;
import ru.tele2.autoct.services.additionalParams.ActivationMethodService;
import java.util.Collections;
import java.util.List;

public class ActivationMethodForm extends ComboBox<ActivationMethodDto> {
    public ActivationMethodForm(ActivationMethodService activationMethodService){
        this.setWidthFull();
        this.setClearButtonVisible(true);
        this.setItemLabelGenerator(ActivationMethodDto::toString);
//        this.setLabel("Выберите зону звонка");
        this.setPlaceholder("Выберите метод активации SIM");
        List<ActivationMethodDto> sortedActivationMethods = activationMethodService.getAll();
//        Collections.sort(sortedZones, (o1, o2) -> {
//            if (Integer.parseInt(o1.getZoneId()) > Integer.parseInt(o2.getZoneId())) {
//                return 1;
//            } else {
//                if (Integer.parseInt(o1.getZoneId()) < Integer.parseInt(o2.getZoneId())) {
//                    return -1;
//                } else return 0;
//            }
//        });
        this.setItems(sortedActivationMethods);
    }

    public AdditionalParamDto getParam(){
        AdditionalParamDto result = new AdditionalParamDto();
        if (this.getValue() != null){
            result.setParamId(this.getValue().getMethod());
            result.setParamValue(this.getValue().getDescription());
        } else return null;
        return result;
    }

    public void setParam(AdditionalParamDto additionalParamDto, ActivationMethodService activationMethodService){
        if (additionalParamDto.getParamId() != null){
            this.setValue(activationMethodService.getById(additionalParamDto.getParamId()));
        }
    }
}
