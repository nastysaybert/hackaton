package ru.tele2.autoct.jpa.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tele2.autoct.jpa.entity.security.RoleEntity;

import java.util.List;


public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    List<RoleEntity> findAll();
}
