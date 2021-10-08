package ru.tele2.autoct.dto.additionalParams;

import ru.tele2.autoct.enums.ParamType;

public class BranchDto {

    private Long branchId;

    private String branchName;

    private final ParamType paramType = ParamType.BRANCH;

    public Long getParamId(){
        return this.branchId;
    }

    public ParamType getParamType(){
        return ParamType.BRANCH;
    };

    public String getParamValue(){
        return this.branchName;
    };

    public void setParamId(Long paramId){
        this.branchId = paramId;
    };

    public void setParamValue(String paramValue){
        this.branchName = paramValue;
    };

    public String toString(){
        return this.branchName
                + " (branch_id = "
                + this.branchId
                + ")";
    }

}
