package com.luv2code.cruddemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(String[] args) {
		/*
		 * CommandLineRunner is used to run code after the Spring application context
		 * has been loaded (after Spring beans have been loaded) and before the
		 * application starts serving requests.\
		 * Often used to perform tasks like initialization, setting up default data
		 */
		return runner -> {
			System.out.println("Hello World");
		};
	}

}
