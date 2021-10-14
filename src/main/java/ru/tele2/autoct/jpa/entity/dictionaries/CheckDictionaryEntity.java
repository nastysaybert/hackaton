package ru.tele2.autoct.jpa.entity.dictionaries;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "CHECK_ACTION_DICTIONARY")
public class CheckDictionaryEntity {
    /**
     * id Действия проверки (проверки результата)
     */
    @Id
    @Column(name = "CHECK_DICT_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long checkDictId;

    /**
     * наименование Действия проверки (проверки результата)
     */
    @Column(name = "CHECK_DICT_NAME", nullable = false)
    private String checkDictName;

    /**
     * связка с Действием BTE
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "BTE_DICT_ID")
    private BTEDictionaryEntity bteDictionary;

    /**
     * список Действий Абонента
     */
    @JsonIgnore
    @ManyToMany(mappedBy = "checkDicts", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AbonDictionaryEntity> abonDicts;

}
