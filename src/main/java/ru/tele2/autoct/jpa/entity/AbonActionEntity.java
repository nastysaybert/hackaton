package ru.tele2.autoct.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import ru.tele2.autoct.jpa.entity.dictionaries.AbonDictionaryEntity;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "ABON_ACTION")
public class AbonActionEntity {
    /**
     * id записи
     */
    @Id
    @Column(name = "ABON_ACTION_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Действие Абонента
     */
    @OneToOne (optional=false)
    private AbonDictionaryEntity abonDict;

    /**
     * Действие BTE
     */
    @OneToOne (optional=false)
    private BTEActionEntity bteAction;
}
