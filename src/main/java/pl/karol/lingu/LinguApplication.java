package pl.karol.lingu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class LinguApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(LinguApplication.class, args);
        LinguController linguController = ctx.getBean(LinguController.class);
        linguController.mainLoop();
    }

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

}
