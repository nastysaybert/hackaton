package ru.tele2.autoct.jpa.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
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
    @Column(name = "INITIAL_ACTION_NAME", nullable = false)
    private String initialAction;

    /**
     * содержание исходной Проверки
     */
    @Column(name = "INITIAL_CHECK_NAME", nullable = false)
    private String initialCheck;

    /**
     * Тест кейс
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEST_CASE_ID")
    private TestCaseEntity testCase;
}
