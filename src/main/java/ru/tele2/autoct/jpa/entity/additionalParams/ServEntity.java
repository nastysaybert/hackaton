package ru.tele2.autoct.jpa.entity.additionalParams;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.tele2.autoct.enums.ParamType;

import javax.persistence.*;

@Data
@Entity
@Table(name = "SERV")
public class ServEntity{
    /**
     * id услуги из DWH
     */
    @Id
    @Column(name = "SERV_ID", nullable = false)
    private String servId;

    /**
     * наименование услуги из DWH
     */
    @Column(name = "SERV_NAME", nullable = false)
    private String servName;

}
