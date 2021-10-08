package ru.tele2.autoct.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tele2.autoct.jpa.entity.security.RoleEntity;


public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
