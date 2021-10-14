package ru.tele2.autoct.views.components.additionalParams;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import ru.tele2.autoct.dto.additionalParams.ServDto;
import ru.tele2.autoct.services.additionalParams.ServService;
import ru.tele2.autoct.services.additionalParams.ServServiceImpl;

import java.util.Collections;
import java.util.List;

public class ServForm extends ComboBox<ServDto> {

    public ServForm(ServService servService){
        this.setWidthFull();
//        this.setWidth("25%");
        this.setLabel("Выберите услугу");
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
//        servSelection.setItems(sortedServs);
        this.setItems(sortedServs);
    }
}
