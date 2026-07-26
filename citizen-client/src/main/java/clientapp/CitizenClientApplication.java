package clientapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import configuration.ApiConfiguration;

@SpringBootApplication
@EnableConfigurationProperties(ApiConfiguration.class)
public class CitizenClientApplication implements CommandLineRunner {

    private final MenuService menuService;

    public CitizenClientApplication(MenuService menuService) {
        this.menuService = menuService;
    }

    public static void main(String[] args) {
        SpringApplication.run(CitizenClientApplication.class, args);
    }

    @Override
    public void run(String... args) {
        menuService.start();
    }
}