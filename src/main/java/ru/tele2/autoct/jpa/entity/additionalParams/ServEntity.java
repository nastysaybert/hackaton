package ru.tele2.autoct.jpa.entity.additionalParams;

import lombok.Getter;
import lombok.Setter;
import ru.tele2.autoct.enums.ParamType;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "SERV")
public class ServEntity{
    /**
     * id услуги из DWH
     */
    @Id
    @Column(name = "SERV_ID", nullable = false)
    private Long servId;

    /**
     * наименование услуги из DWH
     */
    @Column(name = "SERV_NAME", nullable = false)
    private String servName;

    /**
     * тип параметра
     */
    @Column(name = "SERV_PARAM_TYPE", nullable = false)
    private ParamType paramType = ParamType.SERV;

}
