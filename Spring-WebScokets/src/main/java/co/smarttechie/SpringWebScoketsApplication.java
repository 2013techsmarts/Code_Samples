package co.smarttechie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SpringWebScoketsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebScoketsApplication.class, args);
		log.info("Spring Boot Websockets Application started");

	}

}
