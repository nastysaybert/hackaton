package ru.tele2.autoct.jpa.entity.dictionaries;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BTE_DICT_ID")
    private BTEDictionaryEntity bteDictionary;

    /**
     * список Действий проверки (проверки результата)
     */
    @ManyToMany()
    @JoinTable(
            name = "abon_check",
            joinColumns = @JoinColumn(name = "ABON_DICT_ID"),
            inverseJoinColumns = @JoinColumn(name = "CHECK_DICT_ID"))
    private Set<CheckDictionaryEntity> checkDicts;

}
