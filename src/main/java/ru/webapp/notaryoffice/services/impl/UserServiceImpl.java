package ru.webapp.notaryoffice.services.impl;

import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.webapp.notaryoffice.dto.UserDto;
import ru.webapp.notaryoffice.entity.Role;
import ru.webapp.notaryoffice.entity.User;
import ru.webapp.notaryoffice.repositories.RoleRepository;
import ru.webapp.notaryoffice.repositories.UserRepository;
import ru.webapp.notaryoffice.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName());
        user.setSurname(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_USER");
        user.setRoles(List.of(role));

        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }
    private UserDto convertEntityToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getName());
        userDto.setLastName(user.getSurname());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
