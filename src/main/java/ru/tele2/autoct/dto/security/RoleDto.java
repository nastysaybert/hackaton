package ru.tele2.autoct.dto.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import ru.tele2.autoct.jpa.entity.security.UserEntity;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class RoleDto implements GrantedAuthority {

    private Long roleId;

    private String roleName;

    private Set<UserEntity> users;

    @Override
    public String getAuthority() {
        return getRoleName();
    }

    public RoleDto(Long id) {
        this.roleId = id;
    }

    public RoleDto(Long id, String name) {
        this.roleId = id;
        this.roleName = name;
    }
}
