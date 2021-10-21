package ru.tele2.autoct.jpa.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Data
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
     * является ли шаблоном
     */
    @Column(name = "TEST_CASE_NAME", nullable = false)
    private String name;

    /**
     * Исходные данные
     */
    @OneToOne (mappedBy = "testCase",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "INITIAL_DATA_ID")
    private InitialDataEntity initialData;

    /**
     * Исходные данные
     */
    @OneToMany(mappedBy = "testCase", cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval=true)
    private List<TestCaseStepEntity> testCaseStepList;

    /**
     * является ли шаблоном
     */
    @Column(name = "IS_TEMPLATE", nullable = false)
    private boolean isTemplate;

}
