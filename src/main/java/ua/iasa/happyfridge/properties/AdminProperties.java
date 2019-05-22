package ua.iasa.happyfridge.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("admin")
@Data
@Component
public class AdminProperties {
    private String email;
}


