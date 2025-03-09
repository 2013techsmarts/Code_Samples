package co.smarttechie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringGRpcApplication {

	private static final Logger logger = LoggerFactory.getLogger(SpringGRpcApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringGRpcApplication.class, args);
		logger.info("Spring Boot Application started");
	}
}
