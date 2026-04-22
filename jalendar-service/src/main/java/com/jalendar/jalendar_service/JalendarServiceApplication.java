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
			if(userRepo.count() == 0) {
				User user = new User();
				user.setEmail("test@test.com");
				user.setPassword("password");
				userRepo.save(user);
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(JalendarServiceApplication.class, args);
	}

}
