package ru.tele2.autoct.jpa.repository.additionalParams;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tele2.autoct.jpa.entity.additionalParams.ServEntity;

public interface ServRepository extends JpaRepository<ServEntity, Long> {
}
