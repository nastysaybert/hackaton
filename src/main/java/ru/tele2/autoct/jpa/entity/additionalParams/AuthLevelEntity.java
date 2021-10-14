package ru.tele2.autoct.jpa.entity.additionalParams;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.tele2.autoct.enums.ParamType;
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
