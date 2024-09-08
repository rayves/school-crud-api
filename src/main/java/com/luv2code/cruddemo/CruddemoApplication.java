package com.luv2code.cruddemo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

import com.luv2code.cruddemo.dto.StudentDTO;
import com.luv2code.cruddemo.model.Student;
import com.luv2code.cruddemo.service.StudentService;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	// @Bean
	// public CommandLineRunner commandLineRunner(StudentService studentService) {
	// /*
	// * CommandLineRunner is used to run code after the Spring application context
	// * has been loaded (after Spring beans have been loaded) and before the
	// * application starts serving requests.\
	// * Often used to perform tasks like initialization, setting up default data
	// */
	// return runner -> {
	// studentService.resetStudents();

	// WebClient client = WebClient.create("https://jsonplaceholder.typicode.com");

	// List<StudentDTO> response = client.get()
	// .uri("/users")
	// .retrieve()
	// .bodyToMono(new ParameterizedTypeReference<List<StudentDTO>>() {
	// })
	// .block();

	// studentService.createStudents(response);
	// Student myStudent =
	// studentService.queryByStudentEmail("rey.padberg@karina.biz");
	// studentService.queryForAllStudents();
	// studentService.updateStudent(myStudent);
	// System.out.println("APP READY FOR REQUESTS");
	// };
	// }

}
