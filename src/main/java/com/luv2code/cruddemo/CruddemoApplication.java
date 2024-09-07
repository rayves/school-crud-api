package com.luv2code.cruddemo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

import com.luv2code.cruddemo.DAO.StudentDAO;
import com.luv2code.cruddemo.DTO.StudentDTO;
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
			resetStudents(studentDAO);

			WebClient client = WebClient.create("https://jsonplaceholder.typicode.com");

			List<StudentDTO> response = client.get()
					.uri("/users")
					.retrieve()
					.bodyToMono(new ParameterizedTypeReference<List<StudentDTO>>() {
					})
					.block();

			createStudents(studentDAO, response);
			Student myStudent = queryByStudentEmail(studentDAO, "rey.padberg@karina.biz");
			System.out.println("Found the student " + myStudent);
			queryForStudents(studentDAO);

			updateStudent(studentDAO, myStudent);
			System.out.println("Updated Student... " + myStudent);
		};
	}

	private void createStudents(StudentDAO studentDAO, List<StudentDTO> students) {
		System.out.println("Creating all new students...");
		for (StudentDTO student : students) {
			System.out.println("Creating new student object...");
			String firstName = student.getFullName().split(" ")[0].toLowerCase();
			String lastName = student.getFullName().split(" ")[1].toLowerCase();
			Student tempStudent = new Student(firstName, lastName, student.getEmail().toLowerCase());
			System.out.println("Saving new student...");
			studentDAO.save(tempStudent);
			System.out.println("Saved student. Generated Id: " + tempStudent.getId());
		}
	}

	private void updateStudent(StudentDAO studentDAO, Student myStudent) {
		System.out.println("Updating student..." + myStudent);
		myStudent.setLastName("TEST");
		studentDAO.update(myStudent);
	}

	private Student queryByStudentId(StudentDAO studentDAO, int id) {
		System.out.println("Retrieving student by id..." + id);
		return studentDAO.findById(id);
	}

	private Student queryByStudentEmail(StudentDAO studentDAO, String email) {
		System.out.println("Retrieving student by Email..." + email);
		return studentDAO.findByEmail(email);
	}

	private void queryForStudents(StudentDAO studentDAO) {
		System.out.println("Retrieving all students...");
		List<Student> students = studentDAO.findAll();
		for (Student student : students) {
			System.out.println(student);
		}
	}

	private void resetStudents(StudentDAO studentDAO) {
		System.out.println("Cleaning Students Table in Database...");
		studentDAO.deleteAll();
	}
}
