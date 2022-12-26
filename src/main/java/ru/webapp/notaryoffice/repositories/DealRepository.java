package ru.webapp.notaryoffice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.webapp.notaryoffice.entity.Deal;

public interface DealRepository extends JpaRepository<Deal, Long> {
}
