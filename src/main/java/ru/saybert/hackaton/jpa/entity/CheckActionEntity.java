package ru.saybert.hackaton.jpa.entity;

import lombok.Data;
import ru.saybert.hackaton.jpa.entity.dictionaries.CheckDictionaryEntity;

import javax.persistence.*;
import java.util.List;

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
    @OneToMany(mappedBy = "checkAction", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval=true)
    private List<BTEActionEntity> bteActions;

    /**
     * Тест кейс
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEST_CASE_STEP_ID")
    private TestCaseStepEntity testCaseStep;

    @Lob
    @Column(name = "CHECK_ACTION_COMMENT")
    private String comment;
}
