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
    @OneToOne (optional=false)
    private AbonActionEntity abonAction;

    /**
     * Действиe абонента
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "TEST_CASE_STEP_ID")
    private List<CheckActionEntity> checkActions;

    /**
     * Тест кейс
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEST_CASE_ID")
    private TestCaseEntity testCase;

}
