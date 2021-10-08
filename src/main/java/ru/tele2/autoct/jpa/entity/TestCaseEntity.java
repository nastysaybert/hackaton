package ru.tele2.autoct.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "TEST_CASE")
public class TestCaseEntity {
    /**
     * id ТК
     */
    @Id
    @Column(name = "TEST_CASE_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Исходные данные
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "TEST_CASE_ID")
    private List<InitialDataEntity> initialDataList;

    /**
     * Исходные данные
     */
//    @OneToMany(mappedBy="TEST_CASE", fetch = FetchType.LAZY)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "TEST_CASE_ID")
    private List<TestCaseStepEntity> testCaseStepList;

    /**
     * является ли шаблоном
     */
    @Column(name = "IS_TEMPLATE", nullable = false)
    private boolean isTemplate;

}
