package ru.tele2.autoct.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import ru.tele2.autoct.enums.ParamType;

import javax.persistence.*;

@Getter
@Setter
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
     * Тип ключевого параметра для Действия BTE
     */
    @Column(name = "BTE_ACTION_PARAM", nullable = false)
    @Enumerated(EnumType.STRING)
    private ParamType paramType;

    /**
     * Id  ключевого параметра
     */
    @Column(name = "ADD_PARAM_ID", nullable = false)
    private Long paramId;

    /**
     * Значение параметра
     */
    @Column(name = "ADD_PARAM_VALUE", nullable = false)
    private String paramValue;

}
