package ru.saybert.hackaton.jpa.entity.additionalParams;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "NOTIF")
public class NotifEntity {

    /**
     * id нотификации из DWH
     */
    @Id
    @Column(name = "NOTIF_ID", nullable = false)
    private String notifId;

    /**
     * наименование нотификации из DWH
     */
    @Column(name = "NOTIF_NAME", nullable = false)
    private String notifName;

}
