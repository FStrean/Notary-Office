package ru.webapp.notaryoffice.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.webapp.notaryoffice.entity.Service;

public interface ServiceRepository extends JpaRepository<Service, Long> {
    Service findByName(String name);
}
