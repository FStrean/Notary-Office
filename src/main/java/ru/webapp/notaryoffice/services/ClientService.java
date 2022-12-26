package ru.webapp.notaryoffice.services;

import ru.webapp.notaryoffice.dto.ClientDto;
import ru.webapp.notaryoffice.entity.Client;

import java.util.List;

public interface ClientService {
    void saveClient(ClientDto clientDto);

    Client findByPhoneNumber(String phoneNumber);

    List<ClientDto> findAllClients();

    ClientDto findById(Long id);
}
