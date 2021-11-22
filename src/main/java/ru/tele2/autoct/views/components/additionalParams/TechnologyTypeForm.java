package ru.tele2.autoct.views.components.additionalParams;

import com.vaadin.flow.component.combobox.ComboBox;
import ru.tele2.autoct.dto.AdditionalParamDto;
import ru.tele2.autoct.dto.additionalParams.TechnologyTypeDto;
import ru.tele2.autoct.services.additionalParams.TechnologyTypeService;

public class TechnologyTypeForm extends ComboBox<TechnologyTypeDto> {

    public TechnologyTypeForm(TechnologyTypeService technologyTypeService){
        this.setWidthFull();
        this.setClearButtonVisible(true);
        this.setPlaceholder("Выберите тип технологии");
        this.setItemLabelGenerator(TechnologyTypeDto::toString);
        this.setItems(technologyTypeService.getAll());
    }

    public AdditionalParamDto getParam(){
        AdditionalParamDto result = new AdditionalParamDto();
        if (this.getValue() != null){
            result.setParamId(this.getValue().getTechnologyTypeId());
            result.setParamValue(this.getValue().getTechnologyTypeName());
        } else return null;
        return result;
    }

    public void setParam(AdditionalParamDto additionalParamDto, TechnologyTypeService technologyTypeService){
        if (additionalParamDto.getParamId() != null){
            this.setValue(technologyTypeService.getById(additionalParamDto.getParamId()));
        }
    }
}