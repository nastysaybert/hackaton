package ru.saybert.hackaton.jpa.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.saybert.hackaton.jpa.entity.security.UserEntity;


public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
