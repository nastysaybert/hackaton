package ru.saybert.hackaton.jpa.entity;

import lombok.Data;
import ru.saybert.hackaton.enums.ParamType;

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
//    @Column(name = "BTE_ACTION_NAME", nullable = false)
//    private String name;
    @Column(name = "BTE_ACTION_NAME", nullable = false)
    @Enumerated(EnumType.STRING)
    private ParamType paramType;


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


//    @OneToOne (mappedBy = "bteAction", cascade = CascadeType.ALL)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ABON_ACTION_ID")
    private AbonActionEntity abonAction;

//    @OneToOne (mappedBy = "bteAction", cascade = CascadeType.ALL)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHECK_ACTION_ID")
    private CheckActionEntity checkAction;

//    @OneToMany(mappedBy = "bteAction", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval=true)
//    private List<AdditionalParamEntity> additionalParams;

}
