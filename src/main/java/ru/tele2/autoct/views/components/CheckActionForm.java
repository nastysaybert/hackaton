package ru.tele2.autoct.views.components;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import ru.tele2.autoct.dto.CheckActionDto;
import ru.tele2.autoct.dto.dictionaries.AbonDictionaryDto;
import ru.tele2.autoct.dto.dictionaries.CheckDictionaryDto;
import ru.tele2.autoct.services.dictionaries.CheckDictionaryService;

import java.util.List;

public class CheckActionForm extends ComboBox<CheckDictionaryDto> {

    public CheckActionForm(AbonDictionaryDto abonDictionaryDto,
                           CheckDictionaryService checkDictionaryService){
        List<CheckDictionaryDto> checkDictList = checkDictionaryService.getAllByAbonDict(abonDictionaryDto);
        this.setLabel("Выберите действие проверки");
        this.setWidthFull();
        this.setClearButtonVisible(true);
        this.setItemLabelGenerator(CheckDictionaryDto::getCheckDictName);
        this.setItems(checkDictList);
        this.setId("CheckActionForm");
    }

    public CheckDictionaryDto getCheckDictionaryDto(){
        return this.getValue();
    }

    public boolean isValid(){
        if (this.isEmpty()){
            Notification.show("Заполните Действие проверки")
                    .addThemeVariants(NotificationVariant.LUMO_ERROR);
            return false;
        } else return true;
    }
}
