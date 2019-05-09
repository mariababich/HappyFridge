package ua.iasa.happyfridge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.iasa.happyfridge.dto.UserRegistrationRequest;
import ua.iasa.happyfridge.entities.Role;
import ua.iasa.happyfridge.entities.User;
import ua.iasa.happyfridge.repositories.RoleRepository;
import ua.iasa.happyfridge.repositories.UserRepository;

import java.util.Collections;

import static java.util.Collections.singleton;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

    @PostMapping("/registration")
    public ResponseEntity registration(@RequestBody UserRegistrationRequest request) {
        User newUser = new User(request.getUsername(), bCryptPasswordEncoder.encode(request.getPassword()), request.getEmail(), singleton(roleRepository.findByName("USER")));
        userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }
}
