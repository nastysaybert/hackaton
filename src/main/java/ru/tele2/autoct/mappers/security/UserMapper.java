package ru.tele2.autoct.mappers.security;

import org.mapstruct.Mapper;
import ru.tele2.autoct.dto.security.UserDto;
import ru.tele2.autoct.jpa.entity.security.UserEntity;

@Mapper(
        componentModel = "spring",
        uses = {
                RoleMapper.class
        }
)
public interface UserMapper {
    UserDto convert(UserEntity user);
    UserEntity convert(UserDto user);
}