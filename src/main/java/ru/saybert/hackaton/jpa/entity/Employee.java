package ru.saybert.hackaton.jpa.entity;

import lombok.Data;
import ru.saybert.hackaton.enums.MessageCategory;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    @ManyToMany
    private List<MessageCategory> category;

    @Column(name = "messages")
    @OneToMany(mappedBy = "assigned_employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Message> messages;
}
