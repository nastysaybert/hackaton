package ru.saybert.hackaton.jpa.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.saybert.hackaton.jpa.entity.security.RoleEntity;

import java.util.List;


public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    List<RoleEntity> findAll();
}
