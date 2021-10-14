package ru.tele2.autoct.views.components.additionalParams;

import com.vaadin.flow.component.combobox.ComboBox;
import ru.tele2.autoct.dto.additionalParams.NotifDto;
import ru.tele2.autoct.services.additionalParams.NotifService;

import java.util.Collections;
import java.util.List;

public class NotifForm extends ComboBox<NotifDto> {

    public NotifForm(NotifService notifService){
        this.setWidthFull();
//        this.setWidth("25%");
        this.setClearButtonVisible(true);
        this.setLabel("Выберите нотификацию");
        this.setItemLabelGenerator(NotifDto::toString);
        List<NotifDto> sortedNotifs = notifService.getAll();
        Collections.sort(sortedNotifs, (o1, o2) -> {
            if (Integer.parseInt(o1.getNotifId()) > Integer.parseInt(o2.getNotifId())) {
                return 1;
            } else {
                if (Integer.parseInt(o1.getNotifId()) < Integer.parseInt(o2.getNotifId())) {
                    return -1;
                } else return 0;
            }
        });
        this.setItems(sortedNotifs);
    }
}
