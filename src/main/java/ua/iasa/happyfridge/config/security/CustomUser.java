package ua.iasa.happyfridge.config.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import ua.iasa.happyfridge.entities.Order;

import java.util.Collection;
import java.util.List;

public class CustomUser extends User {
    private Long id;

    private String email;

    private List<String> adress;

    public List<String> getAdress() {
        return adress;
    }

    public void setAdress(List<String> adress) {
        this.adress = adress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    private List<Order> orders;

    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities, Long id, String email, List<Order> orders, List<String> adress) {
        super(username, password, authorities);
        this.id = id;
        this.email = email;
        this.orders = orders;
        this.adress = adress;
    }
}
