package ru.tele2.autoct.jpa.entity.dictionaries;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "ABON_ACTION_DICTIONARY")
public class AbonDictionaryEntity {
    /**
     * id Действия Абонента
     */
    @Id
    @Column(name = "ABON_DICT_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long abonDictId;

    /**
     * наименование Действия Абонента
     */
    @Column(name = "ABON_DICT_NAME", nullable = false)
    private String abonDictName;

    /**
     * связка с Действием BTE
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "BTE_DICT_ID")
    private BTEDictionaryEntity bteDictionary;

    /**
     * список Действий проверки (проверки результата)
     */
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "abon_check",
            joinColumns = @JoinColumn(name = "ABON_DICT_ID"),
            inverseJoinColumns = @JoinColumn(name = "CHECK_DICT_ID"))
    private List<CheckDictionaryEntity> checkDicts;
}
