package ru.tele2.autoct.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
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

}
