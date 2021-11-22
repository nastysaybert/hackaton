package ru.tele2.autoct.views.components.additionalParams;

import com.vaadin.flow.component.combobox.ComboBox;
import ru.tele2.autoct.dto.AdditionalParamDto;
import ru.tele2.autoct.dto.additionalParams.ClientTypeDto;
import ru.tele2.autoct.services.additionalParams.ClientTypeService;

public class ClientTypeForm extends ComboBox<ClientTypeDto> {

    public ClientTypeForm(ClientTypeService clientTypeService){
        this.setWidthFull();
        this.setClearButtonVisible(true);
        this.setPlaceholder("Выберите тип клиента");
        this.setItemLabelGenerator(ClientTypeDto::toString);
        this.setItems(clientTypeService.getAll());
    }

    public AdditionalParamDto getParam(){
        AdditionalParamDto result = new AdditionalParamDto();
        if (this.getValue() != null){
            result.setParamId(this.getValue().getClientTypeId());
            result.setParamValue(this.getValue().getClientTypeName());
        } else return null;
        return result;
    }

    public void setParam(AdditionalParamDto additionalParamDto, ClientTypeService clientTypeService){
        if (additionalParamDto.getParamId() != null){
            this.setValue(clientTypeService.getById(additionalParamDto.getParamId()));
        }
    }
}
