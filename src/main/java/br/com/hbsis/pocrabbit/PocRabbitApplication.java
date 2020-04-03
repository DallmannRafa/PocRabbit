package br.com.hbsis.pocrabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class PocRabbitApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocRabbitApplication.class, args);
	}

}