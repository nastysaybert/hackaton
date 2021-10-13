package ru.tele2.autoct.dto.additionalParams;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BranchDto {

    private String branchId;

    private String branchName;

    public String toString(){
        return branchName +
                " (branch_id = " +
                branchId +
                ")";
    }

}
