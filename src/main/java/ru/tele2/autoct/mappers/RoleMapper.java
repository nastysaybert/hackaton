package ru.tele2.autoct.mappers;

import org.mapstruct.Mapper;
import ru.tele2.autoct.dto.security.RoleDto;
import ru.tele2.autoct.jpa.entity.security.RoleEntity;


@Mapper(
        componentModel = "spring",
        uses = {
                UserMapper.class
        }
)

public interface RoleMapper {
    RoleDto convert (RoleEntity role);
    RoleEntity convert (RoleDto role);
}