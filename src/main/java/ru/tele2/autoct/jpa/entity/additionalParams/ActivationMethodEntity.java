package ru.tele2.autoct.jpa.entity.additionalParams;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "ACTIVATION_METHOD")
public class ActivationMethodEntity {
    /**
     * Метод активации
     */
    @Id
    @Column(name = "ACTIVATION_METHOD_ID", nullable = false)
    private String method;

    /**
     * Описание метода активации
     */
    @Column(name = "ACTIVATION_METHOD_DESCRIPTION", nullable = false)
    private String description;
}
