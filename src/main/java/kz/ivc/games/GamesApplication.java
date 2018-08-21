package kz.ivc.games;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class GamesApplication {
    public static void main(String[] args) {
        SpringApplication.run(GamesApplication.class, args);
    }
}