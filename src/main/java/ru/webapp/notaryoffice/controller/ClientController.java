package ru.webapp.notaryoffice.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.webapp.notaryoffice.dto.ClientDto;
import ru.webapp.notaryoffice.entity.Client;
import ru.webapp.notaryoffice.services.ClientService;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping()
    public String getAllClients(Model model) {
        List<ClientDto> clients = clientService.findAllClients();
        model.addAttribute("clients", clients);

        ClientDto client = new ClientDto();
        model.addAttribute("client", client);
        return "tabs/clients";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("client") ClientDto client,
                               BindingResult result,
                               Model model){
        Client existing = clientService.findByPhoneNumber(client.getPhoneNumber());
        if (existing != null) {
            result.rejectValue("phoneNumber", null, "Пользователь с таким телефоном уже существует");
        }
        if (result.hasErrors()) {
            model.addAttribute("client", client);
            return "redirect:/clients";
        }
        clientService.saveClient(client);
        return "redirect:/clients";
    }
}
