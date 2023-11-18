package ru.saybert.hackaton.mappers.security;

import org.mapstruct.Mapper;
import ru.saybert.hackaton.dto.security.UserDto;
import ru.saybert.hackaton.jpa.entity.security.UserEntity;

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