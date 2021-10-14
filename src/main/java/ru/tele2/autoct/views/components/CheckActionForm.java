package ru.tele2.autoct.views.components;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import ru.tele2.autoct.dto.dictionaries.AbonDictionaryDto;
import ru.tele2.autoct.dto.dictionaries.CheckDictionaryDto;
import ru.tele2.autoct.services.additionalParams.*;
import ru.tele2.autoct.services.dictionaries.CheckDictionaryService;
import ru.tele2.autoct.views.components.additionalParams.AdditionalParam;

import java.util.List;

public class CheckActionForm extends HorizontalLayout {
    public CheckActionForm(AbonDictionaryDto abonDictionaryDto,
                           CheckDictionaryService checkDictionaryService,
                           AuthLevelService authLevelService,
                           BranchService branchService,
                           NotifService notifService,
                           ServService servService,
                           TrplService trplService){
        this.setMargin(false);
        this.setPadding(false);
        List<CheckDictionaryDto> checkDictList = checkDictionaryService.getAllByAbonDict(abonDictionaryDto);
        ComboBox<CheckDictionaryDto> checkDictSelection = new ComboBox<>();
        checkDictSelection.setLabel("Выберите действие проверки");
        checkDictSelection.setWidthFull();
        checkDictSelection.setClearButtonVisible(true);
        checkDictSelection.setItemLabelGenerator(CheckDictionaryDto::getCheckDictName);
        checkDictSelection.setItems(checkDictList);
        Div param = new Div();
        checkDictSelection.addValueChangeListener(element ->{
            param.removeAll();
            if (element.getValue() != null){
                param.add(new AdditionalParam(element.getValue().getBteDictionary().getParamType(),
                        authLevelService,branchService,notifService,servService,trplService));
            }
        });
        this.add(checkDictSelection,param);

    }
}
