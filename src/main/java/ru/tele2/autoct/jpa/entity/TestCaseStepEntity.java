package ru.tele2.autoct.jpa.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "TEST_CASE_STEPS")
public class TestCaseStepEntity {
    /**
     * id шага ТК
     */
    @Id
    @Column(name = "TEST_CASE_STEP_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * № шага
     */
    @Column(name = "TEST_CASE_STEP_NUMBER")
    private Long stepNumber;

    /**
     * Действиe абонента
     */
    @OneToOne (optional=false, cascade = CascadeType.ALL)
    @JoinColumn(name = "ABON_ACTION_ID", referencedColumnName = "ABON_ACTION_ID")
    private AbonActionEntity abonAction;

    /**
     * Действиe абонента
     */
    @OneToMany(mappedBy = "testCaseStep", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval=true)
    private List<CheckActionEntity> checkActions;

    /**
     * Тест кейс
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEST_CASE_ID")
    private TestCaseEntity testCase;

}
