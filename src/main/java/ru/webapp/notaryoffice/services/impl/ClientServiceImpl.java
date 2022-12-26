package ru.webapp.notaryoffice.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.webapp.notaryoffice.dto.ClientDto;
import ru.webapp.notaryoffice.entity.Client;
import ru.webapp.notaryoffice.repositories.ClientRepository;
import ru.webapp.notaryoffice.repositories.UserRepository;
import ru.webapp.notaryoffice.services.ClientService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository,
                             UserRepository userRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public void saveClient(ClientDto clientDto) {
        Client client = new Client();
        client.setName(clientDto.getFirstName());
        client.setSurname(clientDto.getLastName());
        client.setAddress(clientDto.getAddress());
        client.setJob(clientDto.getJob());
        client.setPhoneNumber(clientDto.getPhoneNumber());
        clientRepository.save(client);
    }

    @Override
    public Client findByPhoneNumber(String phoneNumber) {
        return clientRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public List<ClientDto> findAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream().map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDto findById(Long id) {
        return convertEntityToDto(clientRepository.findById(id).get());
    }

    private ClientDto convertEntityToDto(Client client){
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setFirstName(client.getName());
        clientDto.setLastName(client.getSurname());
        clientDto.setAddress(client.getAddress());
        clientDto.setPhoneNumber(client.getPhoneNumber());
        clientDto.setJob(client.getJob());
        return clientDto;
    }
}
