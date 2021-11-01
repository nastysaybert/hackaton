package ru.tele2.autoct.views.components.additionalParams;

import com.vaadin.flow.component.combobox.ComboBox;
import ru.tele2.autoct.dto.AdditionalParamDto;
import ru.tele2.autoct.dto.additionalParams.BranchDto;
import ru.tele2.autoct.services.additionalParams.AuthLevelService;
import ru.tele2.autoct.services.additionalParams.BranchService;
import java.util.Collections;
import java.util.List;

public class BranchForm extends ComboBox<BranchDto> {

    public BranchForm(BranchService branchService){
        this.setWidthFull();
        this.setClearButtonVisible(true);
        this.setItemLabelGenerator(BranchDto::toString);
        this.setLabel("Выберите регион");
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
        this.setItems(sortedBranches);
    }

    public AdditionalParamDto getParam(){
        AdditionalParamDto result = new AdditionalParamDto();
        if (this.getValue() != null){
            result.setParamId(this.getValue().getBranchId());
            result.setParamValue(this.getValue().getBranchName());
        } else return null;
        return result;
    }

    public void setParam(AdditionalParamDto additionalParamDto, BranchService branchService){
        if (additionalParamDto.getParamId() != null){
            this.setValue(branchService.getById(additionalParamDto.getParamId()));
        }
    }
}
