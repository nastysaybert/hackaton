package ru.tele2.autoct.views.components;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import ru.tele2.autoct.dto.dictionaries.AbonDictionaryDto;
import ru.tele2.autoct.services.additionalParams.*;
import ru.tele2.autoct.services.dictionaries.AbonDictionaryService;
import ru.tele2.autoct.services.dictionaries.CheckDictionaryService;
import ru.tele2.autoct.views.components.additionalParams.AdditionalParam;
import java.util.List;

public class AbonActionForm extends ComboBox<AbonDictionaryDto> {
    public AbonActionForm (AbonDictionaryService abonDictionaryService){
        List<AbonDictionaryDto> abonDictList = abonDictionaryService.getAll();
        this.setLabel("Выберите действие абонента");
        this.setClearButtonVisible(true);
        this.setItemLabelGenerator(AbonDictionaryDto::getAbonDictName);
        this.setItems(abonDictList);
    }
}
