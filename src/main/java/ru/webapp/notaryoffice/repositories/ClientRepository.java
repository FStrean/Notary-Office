package ru.webapp.notaryoffice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.webapp.notaryoffice.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByPhoneNumber(String phoneNumber);
}
