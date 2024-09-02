package com.luv2code.cruddemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		/*
		 * CommandLineRunner is used to run code after the Spring application context
		 * has been loaded (after Spring beans have been loaded) and before the
		 * application starts serving requests.\
		 * Often used to perform tasks like initialization, setting up default data
		 */
		return runner -> {
			createStudent(studentDAO);
		};
	}

	private void createStudent(StudentDAO studentDAO) {
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Paul", "Doe", "paul@luv2code.com");
		System.out.println("Saving new student...");
		studentDAO.save(tempStudent);
		System.out.println("Saved student. Generated Id: " + tempStudent.getId());
	}

}
