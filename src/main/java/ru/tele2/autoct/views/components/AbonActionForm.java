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

public class AbonActionForm extends VerticalLayout {
    public AbonActionForm (AbonDictionaryService abonDictionaryService,
                               CheckDictionaryService checkDictionaryService,
                               AuthLevelService authLevelService,
                               BranchService branchService,
                               NotifService notifService,
                               ServService servService,
                               TrplService trplService){
        this.setMargin(false);
        this.setPadding(false);
        List<AbonDictionaryDto> abonDictList = abonDictionaryService.getAll();
        ComboBox<AbonDictionaryDto> abonDictSelection = new ComboBox<>();
        abonDictSelection.setWidthFull();
        abonDictSelection.setLabel("Выберите действие абонента");
        abonDictSelection.setClearButtonVisible(true);
        abonDictSelection.setItemLabelGenerator(AbonDictionaryDto::getAbonDictName);
        abonDictSelection.setItems(abonDictList);
        Div param = new Div();
        HorizontalLayout line1 = new HorizontalLayout();
        line1.setMargin(false);
        line1.setPadding(false);
        HorizontalLayout line2 = new HorizontalLayout();
        line2.setMargin(false);
        line2.setPadding(false);
        abonDictSelection.addValueChangeListener(element ->{
            param.removeAll();
            line2.removeAll();
            if (element.getValue() != null){
                param.add(new AdditionalParam(element.getValue().getBteDictionary().getParamType(),
                        authLevelService,branchService,notifService,servService,trplService));
                if (checkDictionaryService.getAllByAbonDict(element.getValue()).size() != 0){
                    line2.add(new CheckActionForm(element.getValue(), checkDictionaryService,
                            authLevelService,branchService,notifService,servService,trplService));
                }
            }

            this.add(line2);
        });
        line1.add(abonDictSelection,param);
        this.add(line1);
    }
}
