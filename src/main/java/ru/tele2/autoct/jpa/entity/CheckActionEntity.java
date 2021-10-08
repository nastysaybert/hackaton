package ru.tele2.autoct.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import ru.tele2.autoct.jpa.entity.dictionaries.CheckDictionaryEntity;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "CHECK_ACTION")
public class CheckActionEntity {
    /**
     * id записи
     */
    @Id
    @Column(name = "CHECK_ACTION_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Действие Абонента
     */
    @OneToOne (optional=false)
    private CheckDictionaryEntity checkDict;

    /**
     * Действие BTE
     */
    @OneToOne (optional=false)
    private BTEActionEntity bteAction;
}