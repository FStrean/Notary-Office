package ru.webapp.notaryoffice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.webapp.notaryoffice.entity.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
