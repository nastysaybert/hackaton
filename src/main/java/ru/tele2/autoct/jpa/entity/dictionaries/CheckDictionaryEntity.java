package ru.tele2.autoct.jpa.entity.dictionaries;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "CHECK_ACTION_DICTIONARY")
public class CheckDictionaryEntity {
    /**
     * id Действия проверки (проверки результата)
     */
    @Id
    @Column(name = "CHECK_ACTION_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long checkDictId;

    /**
     * наименование Действия проверки (проверки результата)
     */
    @Column(name = "CHECK_ACTION_NAME", nullable = false)
    private String checkDictName;

    /**
     * связка с Действием BTE
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BTE_ACTION_ID")
    private BTEDictionaryEntity bteAction;

    /**
     * список Действий Абонента
     */
    @ManyToMany(mappedBy = "checkActions", fetch = FetchType.LAZY)
    private Set<AbonDictionaryEntity> abonDicts;

}
