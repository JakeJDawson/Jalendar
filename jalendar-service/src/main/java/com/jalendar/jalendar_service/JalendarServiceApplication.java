package com.jalendar.jalendar_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JalendarServiceApplication {

	@Bean
	CommandLineRunner init(UserRepository userRepo) {
		return args -> {
			// Placeholder from old debugging code
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(JalendarServiceApplication.class, args);
	}

}
