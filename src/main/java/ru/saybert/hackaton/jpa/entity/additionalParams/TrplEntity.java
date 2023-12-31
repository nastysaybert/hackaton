package ru.saybert.hackaton.jpa.entity.additionalParams;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "TRPL")
public class TrplEntity{
    /**
     * id тарифа из DWH
     */
    @Id
    @Column(name = "TRPL_ID", nullable = false)
    private String trplId;

    /**
     * наименование тарифа из DWH
     */
    @Column(name = "TRPL_NAME", nullable = false)
    private String trplName;

}
