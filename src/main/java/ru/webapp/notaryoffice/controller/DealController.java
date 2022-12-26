package ru.webapp.notaryoffice.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.webapp.notaryoffice.dto.ClientDto;
import ru.webapp.notaryoffice.dto.DealDto;
import ru.webapp.notaryoffice.services.ClientService;
import ru.webapp.notaryoffice.services.DealService;
import ru.webapp.notaryoffice.services.ServiceService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/deals")
public class DealController {

    private final DealService dealService;
    private final ClientService clientService;

    private final ServiceService serviceService;

    @Autowired
    public DealController(DealService dealService, ClientService clientService, ServiceService serviceService) {
        this.dealService = dealService;
        this.clientService = clientService;
        this.serviceService = serviceService;
    }

    @GetMapping()
    public String getAllDeals(Model model) {
        List<DealDto> deals = dealService.findAllDeals();
        model.addAttribute("deals", deals);

        DealDto deal = new DealDto();
        model.addAttribute("deal", deal);
        return "tabs/deals";
    }

    @GetMapping("/{id}")
    public String makeADealForm(@PathVariable("id") Long id, Model model) {
        DealDto dealInfoDto = new DealDto();
        ClientDto clientDto = clientService.findById(id);
        dealInfoDto.setClientPhoneNumber(clientDto.getPhoneNumber());
        dealInfoDto.setClientNameSurname(clientDto.getFirstName() + " " + clientDto.getLastName());
        dealInfoDto.setServiceDtoList(new ArrayList<>());

        model.addAttribute("dealInfo", dealInfoDto);
        model.addAttribute("newDeal", new DealDto());
        model.addAttribute("services", serviceService.findAllServices());
        return "tabs/deal";
    }

    @PostMapping("/save")
    public String newDeal(@Valid @ModelAttribute("newDeal") DealDto deal,
                          BindingResult result,
                          Model model) {
        if (result.hasErrors()) {
            model.addAttribute("newDeal", deal);
            return "redirect:/home";
        }
        dealService.saveDeal(deal);
        return "redirect:/deals";
    }
}
