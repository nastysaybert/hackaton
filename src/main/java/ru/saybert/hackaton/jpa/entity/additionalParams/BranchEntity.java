package ru.saybert.hackaton.jpa.entity.additionalParams;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "BRANCH")
public class BranchEntity {
    /**
     * id региона из DWH
     */
    @Id
    @Column(name = "BRANCH_ID", nullable = false)
    private String branchId;

    /**
     * наименование региона из DWH
     */
    @Column(name = "BRANCH_NAME", nullable = false)
    private String branchName;

}
