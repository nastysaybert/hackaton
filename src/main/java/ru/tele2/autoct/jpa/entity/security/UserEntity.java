package ru.tele2.autoct.jpa.entity.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "USER")
@NoArgsConstructor
public class UserEntity {

    /**
     * id пользователя
     */
    @Id
    @Column(name = "USER_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    /**
     * логин (не менее 5 знаков)
     */
    @Column(name = "USERNAME", nullable = false)
    @Size(min=5, message = "Не меньше 5 знаков")
    private String username;

    /**
     * пароль (не менее 8 знаков)
     */
    @Column(name = "PASSWORD", nullable = false)
    @Size(min=8, message = "Не меньше 8 знаков")
    private String password;

    /**
     * список доступных пользователю ролей
     */
    @ManyToMany()
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<RoleEntity> roles;

}
