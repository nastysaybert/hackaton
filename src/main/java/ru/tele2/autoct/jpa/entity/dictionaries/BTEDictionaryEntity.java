package ru.tele2.autoct.jpa.entity.dictionaries;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.tele2.autoct.enums.ParamType;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "BTE_ACTION_DICTIONARY")
public class BTEDictionaryEntity {
    /**
     * id Действия BTE
     */
    @Id
    @Column(name = "BTE_DICT_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bteDictId;

    /**
     * наименование Действия BTE
     */
    @Column(name = "BTE_DICT_NAME", nullable = false)
    private String bteDictName;

    /**
     * Тип ключевого параметра для Действия BTE
     */
    @Column(name = "BTE_DICT_PARAM", nullable = false)
    @Enumerated(EnumType.STRING)
    private ParamType paramType;

    @OneToMany(mappedBy = "bteDictionary", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AbonDictionaryEntity> abonActionList;

    @OneToMany(mappedBy = "bteDictionary", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CheckDictionaryEntity> checkActionList;

}
