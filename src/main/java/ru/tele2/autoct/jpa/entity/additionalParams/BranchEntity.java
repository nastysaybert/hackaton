package ru.tele2.autoct.jpa.entity.additionalParams;

import lombok.Getter;
import lombok.Setter;
import ru.tele2.autoct.enums.ParamType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "BRANCH")
public class BranchEntity {
    /**
     * id региона из DWH
     */
    @Id
    @Column(name = "BRANCH_ID", nullable = false)
    private Long branchId;

    /**
     * наименование региона из DWH
     */
    @Column(name = "BRANCH_NAME", nullable = false)
    private String branchName;

    /**
     * тип параметра
     */
    @Column(name = "BRANCH_PARAM_TYPE", nullable = false)
    private ParamType paramType = ParamType.BRANCH;

}
