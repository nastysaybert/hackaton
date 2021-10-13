package ru.tele2.autoct.views.components.additionalParams;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import ru.tele2.autoct.dto.additionalParams.TrplDto;
import ru.tele2.autoct.services.TrplService;

import java.util.Collections;
import java.util.List;

public class TrplForm extends FormLayout {
    public TrplForm(TrplService trplService){
        ComboBox<TrplDto> trplSelection = new ComboBox<>();
        trplSelection.setWidthFull();
        trplSelection.setClearButtonVisible(true);
        trplSelection.setItemLabelGenerator(TrplDto::toString);
        List<TrplDto> sortedTrpls = trplService.getAll();
        Collections.sort(sortedTrpls, (o1, o2) -> {
            if (Integer.parseInt(o1.getTrplId()) > Integer.parseInt(o2.getTrplId())) {
                return 1;
            } else {
                if (Integer.parseInt(o1.getTrplId()) < Integer.parseInt(o2.getTrplId())) {
                    return -1;
                } else return 0;
            }
        });
        trplSelection.setItems(sortedTrpls);
        this.addFormItem(trplSelection, "Выберите тарифный план");
    }
}
