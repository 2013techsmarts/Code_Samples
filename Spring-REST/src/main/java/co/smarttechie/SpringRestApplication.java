package co.smarttechie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SpringRestApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringRestApplication.class, args);
        log.info("Spring Boot REST Application started");
    }
} 