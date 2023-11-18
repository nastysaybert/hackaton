package ru.saybert.hackaton.jpa.entity.additionalParams;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "TECHNOLOGY_TYPE")
public class TechnologyTypeEntity {
    /**
     * Id технологии
     */
    @Id
    @Column(name = "TECHNOLOGY_TYPE_ID", nullable = false)
    private String technologyTypeId;

    /**
     * Наименование технологии сети
     */
    @Column(name = "TECHNOLOGY_TYPE_NAME", nullable = false)
    private String technologyTypeName;
}
