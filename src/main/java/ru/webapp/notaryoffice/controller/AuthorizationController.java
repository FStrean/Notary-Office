package ru.webapp.notaryoffice.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.webapp.notaryoffice.dto.UserDto;
import ru.webapp.notaryoffice.entity.User;
import ru.webapp.notaryoffice.services.UserService;

@Controller
@RequestMapping("/sign")
public class AuthorizationController {
    private final UserService userService;

    public AuthorizationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public  String signPage(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "sign";
    }

    @PostMapping("/up/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model){
        User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "Пользователь с таким email уже существует");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "sign";
        }
        userService.saveUser(user);
        return "redirect:/sign";
    }
}
