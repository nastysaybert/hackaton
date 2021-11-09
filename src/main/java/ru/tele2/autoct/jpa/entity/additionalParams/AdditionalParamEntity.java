package ru.tele2.autoct.jpa.entity.additionalParams;

import lombok.Data;
import ru.tele2.autoct.enums.ParamType;
import ru.tele2.autoct.jpa.entity.BTEActionEntity;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ADDITIONAL_PARAM")
public class AdditionalParamEntity {

    /**
     * Наименование уровня полномочий как в DWH
     */
    @Id
    @Column(name = "ADDITIONAL_PARAM_ID", nullable = false)
    private String additionalParamId;

    /**
     * расшифровка
     */
    @Column(name = "ADDITIONAL_PARAM_NAME", nullable = false)
    private String additionalParamName;

//    @Column(name = "BTE_DICT_PARAM", nullable = false)
//    @Enumerated(EnumType.STRING)
//    private ParamType additionalParamType;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "BTE_ACTION_ID")
//    private BTEActionEntity bteAction;

}
