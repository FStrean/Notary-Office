package ru.webapp.notaryoffice.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.webapp.notaryoffice.dto.ServiceDto;
import ru.webapp.notaryoffice.services.ServiceService;

import java.util.List;

@Controller
@RequestMapping("/services")
public class ServiceController {

    private final ServiceService serviceService;

    @Autowired
    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping()
    public String getAllServices(Model model) {
        List<ServiceDto> services = serviceService.findAllServices();
        model.addAttribute("services", services);

        ServiceDto service = new ServiceDto();
        model.addAttribute("service", service);
        return "tabs/services";
    }

    @PostMapping("/add/save")
    public String add(@Valid @ModelAttribute("service") ServiceDto service,
                               BindingResult result,
                               Model model){

        if (result.hasErrors()) {
            model.addAttribute("service", service);
            return "redirect:/home";
        }
        serviceService.saveService(service);
        return "redirect:/services";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute("id") Long id, BindingResult result, Model model){
        serviceService.deleteService(id);
        return "redirect:/services";
    }
}
