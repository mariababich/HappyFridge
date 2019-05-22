package ua.iasa.happyfridge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ua.iasa.happyfridge.properties.AdminProperties;

@SpringBootApplication
@EnableConfigurationProperties({AdminProperties.class})
public class HappyFridgeApplication {

    public static void main(String[] args) {
        SpringApplication.run(HappyFridgeApplication.class, args);
    }

}
