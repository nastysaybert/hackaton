package ru.tele2.autoct.jpa.entity.security;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "ROLE")
public class RoleEntity {

    /**
     * id роли
     */
    @Id
    @Column(name = "ROLE_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    /**
     * наименование роли
     */
    @Column(name = "ROLE_NAME", nullable = false)
    private String roleName;

    /**
     * список пользователей, обладающих ролью
     */
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<UserEntity> users;

}
