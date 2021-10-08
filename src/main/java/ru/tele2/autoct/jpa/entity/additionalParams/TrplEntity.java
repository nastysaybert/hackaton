package ru.tele2.autoct.jpa.entity.additionalParams;

import lombok.Getter;
import lombok.Setter;
import ru.tele2.autoct.enums.ParamType;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "TRPL")
public class TrplEntity{
    /**
     * id тарифа из DWH
     */
    @Id
    @Column(name = "TRPL_ID", nullable = false)
    private Long trplId;

    /**
     * наименование тарифа из DWH
     */
    @Column(name = "TRPL_NAME", nullable = false)
    private String trplName;

    /**
     * тип параметра
     */
    @Column(name = "TRPL_PARAM_TYPE", nullable = false)
    private ParamType paramType = ParamType.TRPL;

}
