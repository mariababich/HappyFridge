package ua.iasa.happyfridge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.orm.hibernate5.SpringSessionContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.iasa.happyfridge.config.security.CustomUser;
import ua.iasa.happyfridge.entities.User;
import ua.iasa.happyfridge.repositories.UserRepository;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(HttpSession session) {
        session.invalidate();
    }

    @RequestMapping("/login")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
    public User login() {
        Long id = ((CustomUser)  SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        return userRepository.getById(id);
    }
}
