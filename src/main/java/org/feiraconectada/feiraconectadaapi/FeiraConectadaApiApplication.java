package org.feiraconectada.feiraconectadaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class FeiraConectadaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeiraConectadaApiApplication.class, args);
	}

}
