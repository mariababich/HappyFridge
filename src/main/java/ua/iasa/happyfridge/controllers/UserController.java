package ua.iasa.happyfridge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ua.iasa.happyfridge.dto.UserRegistrationRequest;
import ua.iasa.happyfridge.entities.Role;
import ua.iasa.happyfridge.entities.User;
import ua.iasa.happyfridge.repositories.RoleRepository;
import ua.iasa.happyfridge.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singleton;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

    @PostMapping("/registration")
    public ResponseEntity<User> registration(@RequestBody UserRegistrationRequest request) {
        User newUser = new User(request.getUsername(),
                bCryptPasswordEncoder.encode(request.getPassword()),
                request.getEmail(), singleton(roleRepository.findByName("USER")), request.getAdressList());
        User save = userRepository.save(newUser);
        return ResponseEntity.ok(save);
    }

    @GetMapping("/users/select")
    public List<User> selectUsers(){
        Iterable<User> all = userRepository.findAll();
        List<User> users = new ArrayList<>();
        all.forEach(users::add);
        return users;
    }

    @PutMapping(path = "/user/update")
    public ResponseEntity updateUser(@RequestBody User user){

        User updateUser = userRepository.save(user);
        return ResponseEntity.ok(updateUser);
    }

}
