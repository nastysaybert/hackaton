package ru.tele2.autoct.views.components;

import com.vaadin.flow.component.combobox.ComboBox;
import ru.tele2.autoct.dto.dictionaries.AbonDictionaryDto;
import ru.tele2.autoct.services.dictionaries.AbonDictionaryService;
import java.util.List;

public class AbonActionForm extends ComboBox<AbonDictionaryDto> {
    public AbonActionForm (AbonDictionaryService abonDictionaryService){
        List<AbonDictionaryDto> abonDictList = abonDictionaryService.getAll();
        this.setLabel("Выберите действие абонента");
        this.setClearButtonVisible(true);
        this.setItemLabelGenerator(AbonDictionaryDto::getAbonDictName);
        this.setItems(abonDictList);
        this.setId("AbonActionForm");
    }

    public AbonDictionaryDto getAbonDictionaryDto(){
        return this.getValue();
    }
}
