package ru.saybert.hackaton.jpa.entity.additionalParams;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "AUTH_LEVEL")
public class AuthLevelEntity {
    /**
     * Наименование уровня полномочий как в DWH
     */
    @Id
    @Column(name = "AUTH_LEVEL_ID", nullable = false)
    private String authLevelId;

    /**
     * расшифровка
     */
    @Column(name = "AUTH_LEVEL_NAME", nullable = false)
    private String authLevelName;
}
