package ru.webapp.notaryoffice.services.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.webapp.notaryoffice.dto.ClientDto;
import ru.webapp.notaryoffice.dto.DealDto;
import ru.webapp.notaryoffice.dto.ServiceDto;
import ru.webapp.notaryoffice.entity.Client;
import ru.webapp.notaryoffice.entity.Deal;
import ru.webapp.notaryoffice.repositories.ClientRepository;
import ru.webapp.notaryoffice.repositories.DealRepository;
import ru.webapp.notaryoffice.repositories.ServiceRepository;
import ru.webapp.notaryoffice.services.DealService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DealServiceImpl implements DealService {

    private final DealRepository dealRepository;
    private final ServiceRepository serviceRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public DealServiceImpl(DealRepository dealRepository,
                           ServiceRepository serviceRepository,
                           ClientRepository clientRepository) {
        this.dealRepository = dealRepository;
        this.serviceRepository = serviceRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public List<DealDto> findAllDeals() {
        List<Deal> deals = dealRepository.findAll();
        return deals.stream().map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void saveDeal(DealDto dealsDto) {
        Deal deal = new Deal();
        deal.setServices(new ArrayList<>());
        for (String string : dealsDto.getServiceDtoList()) {
            ru.webapp.notaryoffice.entity.Service temp = serviceRepository.findByName(string);
            deal.getServices().add(temp);
        }
        deal.setDescription(dealsDto.getDescription());
        Client client = clientRepository.findByPhoneNumber(dealsDto.getClientPhoneNumber());
        deal.setClient(client);
        deal.setAmount(dealsDto.getAmount());
        deal.setCommission_fees(dealsDto.getCommission_fees());
        client.getDeals().add(deal);
        dealRepository.save(deal);
    }

    private DealDto convertEntityToDto(Deal deal){
        DealDto dealDto = new DealDto();
        dealDto.setId(deal.getId());
        dealDto.setClientNameSurname(deal.getClient().getName() + " " + deal.getClient().getSurname());
        dealDto.setAmount(deal.getAmount());
        dealDto.setServiceDtoList(new ArrayList<>());
        for (ru.webapp.notaryoffice.entity.Service service : deal.getServices()) {
            dealDto.getServiceDtoList().add(service.getName());
        }

        dealDto.setDescription(deal.getDescription());
        dealDto.setClientPhoneNumber(deal.getClient().getPhoneNumber());
        dealDto.setCommission_fees(deal.getCommission_fees());
        return dealDto;
    }
}
