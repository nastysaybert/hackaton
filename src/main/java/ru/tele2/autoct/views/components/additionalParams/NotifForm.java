package ru.tele2.autoct.views.components.additionalParams;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import ru.tele2.autoct.dto.additionalParams.NotifDto;
import ru.tele2.autoct.services.NotifService;

import java.util.Collections;
import java.util.List;

public class NotifForm extends FormLayout {

    public NotifForm(NotifService notifService){
        ComboBox<NotifDto> notifSelection = new ComboBox<>();
        notifSelection.setWidthFull();
        notifSelection.setClearButtonVisible(true);
        notifSelection.setItemLabelGenerator(NotifDto::toString);
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
        notifSelection.setItems(sortedNotifs);
        this.addFormItem(notifSelection, "Выберите нотификацию");
    }
}
