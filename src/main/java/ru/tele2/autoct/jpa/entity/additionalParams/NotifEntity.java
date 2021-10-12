package ru.tele2.autoct.jpa.entity.additionalParams;

import lombok.Getter;
import lombok.Setter;
import ru.tele2.autoct.enums.ParamType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
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
