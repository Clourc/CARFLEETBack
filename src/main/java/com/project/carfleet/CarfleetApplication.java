package com.project.carfleet;

import com.project.carfleet.service.DBGenerator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CarfleetApplication {

	private DBGenerator generatorService;

	public CarfleetApplication(DBGenerator generatorService){
		this.generatorService = generatorService;
	}

	public static void main(String[] args) {
		SpringApplication.run(CarfleetApplication.class, args);
	}

	@Bean
	public CommandLineRunner run() throws Exception {
		return (String[] args) -> {
			generatorService.generateRoles();
		};
	}
}





		

