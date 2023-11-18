package ru.saybert.hackaton.jpa.entity;

import lombok.Data;
import ru.saybert.hackaton.enums.MessageCategory;

import javax.persistence.*;

@Data
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "message_text")
    private String messageText;

    @Column(name = "relevance")
    @Enumerated(EnumType.STRING)
    private Relevance relevance;

    @Column(name = "category")
    @ManyToOne
    private MessageCategory category;

    @Column(name = "assigned_employee")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Employee employee;

    public enum Relevance {
        high_priority,
        medium_priority,
        standard_priority
    }
}
