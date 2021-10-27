package ru.tele2.autoct.views.components.additionalParams;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import ru.tele2.autoct.dto.AdditionalParamDto;
import ru.tele2.autoct.dto.additionalParams.TrplDto;
import ru.tele2.autoct.services.additionalParams.ServService;
import ru.tele2.autoct.services.additionalParams.TrplService;

import java.util.Collections;
import java.util.List;

public class TrplForm extends ComboBox<TrplDto> {
    public TrplForm(TrplService trplService){
        this.setWidthFull();
        this.setLabel("Выберите тарифный план");
        this.setClearButtonVisible(true);
        this.setItemLabelGenerator(TrplDto::toString);
        List<TrplDto> sortedTrpls = trplService.getAll();
        Collections.sort(sortedTrpls, (o1, o2) -> {
            StringBuilder o1db = new StringBuilder(o1.getTrplId().substring(1,2));
            StringBuilder o2db = new StringBuilder(o2.getTrplId().substring(1,2));
            StringBuilder o1trplId = new StringBuilder(o1.getTrplId().substring(3));
            StringBuilder o2trplId = new StringBuilder(o2.getTrplId().substring(3));
            if (Integer.parseInt(o1trplId.toString()) > Integer.parseInt(o2trplId.toString())) {
                return 1;
            } else {
                if (Integer.parseInt(o1trplId.toString()) < Integer.parseInt(o2trplId.toString())) {
                    return -1;
                } else {
                    if (Integer.parseInt(o1db.toString()) > Integer.parseInt(o2db.toString())) {
                        return 1;
                    } else {
                        if (Integer.parseInt(o1db.toString()) < Integer.parseInt(o2db.toString())) {
                            return -1;
                        } else return 0;
                    }
                }
            }
        });
        this.setItems(sortedTrpls);
    }

    public AdditionalParamDto getParam(){
        AdditionalParamDto result = new AdditionalParamDto();
        if (this.getValue() != null){
            StringBuilder trplIdBuilder = new StringBuilder(this.getValue().getTrplId());
            trplIdBuilder.delete(0,3);
            result.setParamId(trplIdBuilder.toString());
            result.setParamValue(this.getValue().getTrplName());
        } else return null;
        return result;
    }

    public void setParam(AdditionalParamDto additionalParamDto, TrplService trplService){
        this.setValue(trplService.getById(additionalParamDto.getParamId()));
    }
}
