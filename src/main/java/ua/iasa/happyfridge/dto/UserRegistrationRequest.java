package ua.iasa.happyfridge.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class UserRegistrationRequest {

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String email;

    private List<String> adresses;

    public UserRegistrationRequest() {}

    public UserRegistrationRequest(@NotNull @NotEmpty String username, @NotNull @NotEmpty String password, @NotNull @NotEmpty String email, List<String> adresses) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.adresses = adresses;
    }

    public List<String> getAdresses() {
        return adresses;
    }

    public void setAdresses(List<String> adresses) {
        this.adresses = adresses;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
