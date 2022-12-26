package ru.webapp.notaryoffice.services;


import ru.webapp.notaryoffice.dto.UserDto;
import ru.webapp.notaryoffice.entity.Client;
import ru.webapp.notaryoffice.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();
}
