package ru.webapp.notaryoffice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.webapp.notaryoffice.dto.UserDto;
import ru.webapp.notaryoffice.repositories.RoleRepository;
import ru.webapp.notaryoffice.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final RoleRepository roleRepository;

    @Autowired
    public UserController(UserService userService,
                          RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping()
    public String getAllUsers(Model model) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);

        return "tabs/users";
    }
}
