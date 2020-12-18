package com.ahorcado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = { "com.ahorcado.repo" })
@SpringBootApplication(scanBasePackages = {"com.ahorcado.controller", "com.ahorcado.service"})
@EntityScan(basePackages = { "com.ahorcado.entity" })
public class AhorcadoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AhorcadoApplication.class, args);
	}

}
