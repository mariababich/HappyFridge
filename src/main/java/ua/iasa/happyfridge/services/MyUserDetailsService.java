package ua.iasa.happyfridge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.iasa.happyfridge.config.security.CustomUser;
import ua.iasa.happyfridge.entities.Role;
import ua.iasa.happyfridge.entities.User;
import ua.iasa.happyfridge.repositories.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Service
@ComponentScan
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    @Lazy
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new CustomUser(user.getUsername(), user.getPassword(), grantedAuthorities, user.getId(), user.getEmail(), user.getOrders(), user.getAdresses());
    }
}
