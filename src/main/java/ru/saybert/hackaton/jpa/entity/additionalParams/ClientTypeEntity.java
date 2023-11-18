package ru.saybert.hackaton.jpa.entity.additionalParams;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "CLIENT_TYPE")
public class ClientTypeEntity {
    /**
     * Id типа клиента из в DWH
     */
    @Id
    @Column(name = "CLIENT_TYPE_ID", nullable = false)
    private String clientTypeId;

    /**
     * Наименование типа клиента из в DWH
     */
    @Column(name = "CLIENT_TYPE_NAME", nullable = false)
    private String clientTypeName;
}
