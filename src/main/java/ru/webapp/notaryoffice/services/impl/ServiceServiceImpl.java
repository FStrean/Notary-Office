package ru.webapp.notaryoffice.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.webapp.notaryoffice.dto.ServiceDto;
import ru.webapp.notaryoffice.entity.Service;
import ru.webapp.notaryoffice.repositories.ServiceRepository;
import ru.webapp.notaryoffice.services.ServiceService;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public List<ServiceDto> findAllServices() {
        List<Service> services = serviceRepository.findAll();
        return services.stream().map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void saveService(ServiceDto serviceDto) {
        Service service = new Service();
        service.setName(serviceDto.getName());
        service.setDescription(serviceDto.getDescription());
        serviceRepository.save(service);
    }

    @Override
    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }

    private ServiceDto convertEntityToDto(Service service){
        ServiceDto serviceDto = new ServiceDto();
        serviceDto.setId(service.getId());
        serviceDto.setName(service.getName());
        serviceDto.setDescription(service.getDescription());
        return serviceDto;
    }
}
