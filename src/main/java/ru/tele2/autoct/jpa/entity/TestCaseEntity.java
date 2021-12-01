package ru.tele2.autoct.jpa.entity;

import lombok.Data;
import ru.tele2.autoct.jpa.entity.dictionaries.AbonDictionaryEntity;
import ru.tele2.autoct.jpa.entity.dictionaries.ProjectEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    @Lob
    @Column(name = "TEST_CASE_NAME", nullable = false, length = 1000)
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
    private boolean template;

    /**
     * Дата условного удаления тест-кейса
     */
    @Column(name = "DEL_DATE")
    private LocalDateTime delDate;

    /**
     * Автор
     */
    @Column(name = "AUTHOR")
    private String author;

    /**
     * Последний редактор
     */
    @Column(name = "REDACTOR")
    private String redactor;

    @OneToOne
    @JoinColumn(name = "PROJECT_ID",referencedColumnName = "PROJECT_ID")
    private ProjectEntity project;

}
