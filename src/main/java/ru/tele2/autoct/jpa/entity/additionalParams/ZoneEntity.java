package ru.tele2.autoct.jpa.entity.additionalParams;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "ZONE")
public class ZoneEntity {
    /**
     * id зоны вызова из DWH
     */
    @Id
    @Column(name = "ZONE_ID", nullable = false)
    private String zoneId;

    /**
     * наименование зоны вызова из DWH
     */
    @Column(name = "ZONE_NAME", nullable = false)
    private String zoneName;
}
