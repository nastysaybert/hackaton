package ru.tele2.autoct.views.components.additionalParams;

import com.vaadin.flow.component.combobox.ComboBox;
import ru.tele2.autoct.dto.AdditionalParamDto;
import ru.tele2.autoct.dto.additionalParams.ServDto;
import ru.tele2.autoct.services.additionalParams.ServService;
import java.util.Collections;
import java.util.List;

public class ServForm extends ComboBox<ServDto> {

    public ServForm(ServService servService){
        this.setWidthFull();
        this.setPlaceholder("Выберите услугу");
        this.setClearButtonVisible(true);
        this.setItemLabelGenerator(ServDto::toString);
        List<ServDto> sortedServs = servService.getAll();
        Collections.sort(sortedServs, (o1, o2) -> {
            if (Integer.parseInt(o1.getServId()) > Integer.parseInt(o2.getServId())) {
                return 1;
            } else {
                if (Integer.parseInt(o1.getServId()) < Integer.parseInt(o2.getServId())) {
                    return -1;
                } else return 0;
            }
        });
        this.setItems(sortedServs);
    }

    public AdditionalParamDto getParam(){
        AdditionalParamDto result = new AdditionalParamDto();
        if (this.getValue()!=null){
            result.setParamId(this.getValue().getServId());
            result.setParamValue(this.getValue().getServName());
        } else return null;
        return result;
    }

    public void setParam(AdditionalParamDto additionalParamDto, ServService servService){
        if (additionalParamDto.getParamId() != null){
            this.setValue(servService.getById(additionalParamDto.getParamId()));
        }
    }
}
