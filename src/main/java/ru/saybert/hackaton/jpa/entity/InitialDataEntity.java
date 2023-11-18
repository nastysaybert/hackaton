package ru.saybert.hackaton.jpa.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "INITIAL_DATA")
public class InitialDataEntity {
    /**
     * id записи об исходных данных
     */
    @Id
    @Column(name = "INITIAL_DATA_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * содержание исходного Действия
     */
    @Lob
    @Column(name = "INITIAL_ACTION_NAME")
    private String initialAction;

    /**
     * содержание исходной Проверки
     */
    @Lob
    @Column(name = "INITIAL_CHECK_NAME")
    private String initialCheck;

    @OneToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "TEST_CASE_ID")
    private TestCaseEntity testCase;
}
