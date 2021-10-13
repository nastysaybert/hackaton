package ru.tele2.autoct.views.components.additionalParams;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import ru.tele2.autoct.dto.additionalParams.BranchDto;
import ru.tele2.autoct.services.BranchService;
import java.util.Collections;
import java.util.List;

public class BranchForm extends FormLayout {

    public BranchForm(BranchService branchService){
        ComboBox<BranchDto> branchSelection = new ComboBox<>();
        branchSelection.setWidthFull();
        branchSelection.setClearButtonVisible(true);
        branchSelection.setItemLabelGenerator(BranchDto::toString);
        List<BranchDto> sortedBranches = branchService.getAll();
        Collections.sort(sortedBranches, (o1, o2) -> {
            if (Integer.parseInt(o1.getBranchId()) > Integer.parseInt(o2.getBranchId())) {
                return 1;
            } else {
                if (Integer.parseInt(o1.getBranchId()) < Integer.parseInt(o2.getBranchId())) {
                    return -1;
                } else return 0;
            }
        });
        branchSelection.setItems(sortedBranches);
        this.addFormItem(branchSelection, "Выберите регион");
    }
}
