package ru.tele2.autoct.jpa.entity;

import lombok.Data;
import ru.tele2.autoct.jpa.entity.dictionaries.AbonDictionaryEntity;
import javax.persistence.*;

@Data
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
    @OneToOne
    @JoinColumn(name = "ABON_DICT_ID",referencedColumnName = "ABON_DICT_ID")
    private AbonDictionaryEntity abonDict;

    /**
     * Действие BTE
     */
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "BTE_ACTION_ID",referencedColumnName = "BTE_ACTION_ID")
    private BTEActionEntity bteAction;

    /**
     * Тест кейс
     */
    @OneToOne(mappedBy = "abonAction", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private TestCaseStepEntity testCaseStep;

    @Lob
    @Column(name = "ABON_ACTION_COMMENT")
    private String comment;
}
