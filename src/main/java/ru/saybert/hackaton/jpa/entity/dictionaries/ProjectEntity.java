package ru.saybert.hackaton.jpa.entity.dictionaries;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "PROJECT")
public class ProjectEntity {

    /**
     * ID проекта
     */
    @Id
    @Column(name = "PROJECT_ID", nullable = false)
    private Long projectId;

    /**
     * Название проекта
     */
    @Column(name = "PROJECT_NAME", nullable = false)
    private String projectName;
}
