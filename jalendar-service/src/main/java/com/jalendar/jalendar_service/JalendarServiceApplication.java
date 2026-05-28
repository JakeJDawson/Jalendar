package com.jalendar.jalendar_service;

// Import statements.
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

/**
 * This is the primary file which launches the entire backend. It doesn't do or
 * contain much on its own, but it starts up SpringBoot. This is the file that
 * I run to start up the application.
 */
@SpringBootApplication
public class JalendarServiceApplication {

    /**
     * This can be helpful because the contents of this function will run
     * immediately after launching the backend, so it can be used to seed test
     * data. It was a lot more helpful before I integrated PostgreSQL, and made
     * data in the application persistent, but it's worth keeping for now.
     */
	@Bean
	CommandLineRunner init(UserRepository userRepo) {
		return args -> {
			// Placeholder from old debugging code
		};
	}

	public static void main(String[] args) {
        // This is the line that actually starts up Spring.
		SpringApplication.run(JalendarServiceApplication.class, args);
	}

}
