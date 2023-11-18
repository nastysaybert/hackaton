package ru.saybert.hackaton.mappers.security;

import org.mapstruct.Mapper;
import ru.saybert.hackaton.dto.security.RoleDto;
import ru.saybert.hackaton.jpa.entity.security.RoleEntity;


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