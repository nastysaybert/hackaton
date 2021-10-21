package ru.tele2.autoct.jpa.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.tele2.autoct.jpa.entity.dictionaries.CheckDictionaryEntity;
import javax.persistence.*;

@Data
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
    @OneToOne
    @JoinColumn(name = "CHECK_DICT_ID", referencedColumnName = "CHECK_DICT_ID")
    private CheckDictionaryEntity checkDict;

    /**
     * Действие BTE
     */
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "BTE_ACTION_ID", referencedColumnName = "BTE_ACTION_ID")
    private BTEActionEntity bteAction;

    /**
     * Тест кейс
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEST_CASE_STEP_ID")
    private TestCaseStepEntity testCaseStep;
}
