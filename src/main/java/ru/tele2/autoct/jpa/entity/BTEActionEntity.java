package ru.tele2.autoct.jpa.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.tele2.autoct.enums.ParamType;

import javax.persistence.*;

@Data
@Entity
@Table(name = "BTE_ACTION")
public class BTEActionEntity {
    /**
     * id Действия BTE
     */
    @Id
    @Column(name = "BTE_ACTION_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * наименование Действия BTE
     */
    @Column(name = "BTE_ACTION_NAME", nullable = false)
    private String name;

    /**
     * Id  ключевого параметра
     */
    @Column(name = "ADD_PARAM_ID", nullable = false)
    private String paramId;

    /**
     * Значение параметра
     */
    @Column(name = "ADD_PARAM_VALUE", nullable = false)
    private String paramValue;


    @OneToOne (mappedBy = "bteAction", cascade = CascadeType.ALL)
    private AbonActionEntity abonAction;

    @OneToOne (mappedBy = "bteAction", cascade = CascadeType.ALL)
    private CheckActionEntity checkAction;

}
