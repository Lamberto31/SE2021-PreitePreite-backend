package it.unisalento.mylinkedin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MyLinkedInApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyLinkedInApplication.class, args);
    }

}
