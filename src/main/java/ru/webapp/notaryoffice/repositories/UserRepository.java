package ru.webapp.notaryoffice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.webapp.notaryoffice.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
