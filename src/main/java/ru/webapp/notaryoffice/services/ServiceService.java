package ru.webapp.notaryoffice.services;

import ru.webapp.notaryoffice.dto.ServiceDto;

import java.util.List;


public interface ServiceService {
    List<ServiceDto> findAllServices();

    void saveService(ServiceDto serviceDto);

    void deleteService(Long id);
}
