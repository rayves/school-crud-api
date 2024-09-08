package com.luv2code.cruddemo.startup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.luv2code.cruddemo.dto.StudentDTO;
import com.luv2code.cruddemo.model.Student;
import com.luv2code.cruddemo.service.StudentService;

@Component
public class StartupApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    private final StudentService studentService;
    private boolean hasRun = false;

    @Autowired
    public StartupApplicationListener(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (!hasRun) {
            studentService.resetStudents();

            WebClient client = WebClient.create("https://jsonplaceholder.typicode.com");

            List<StudentDTO> response = client.get()
                    .uri("/users")
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<StudentDTO>>() {
                    })
                    .block();

            studentService.createStudents(response);
            Student myStudent = studentService.queryByStudentEmail("rey.padberg@karina.biz");
            studentService.queryForAllStudents();
            studentService.updateStudent(myStudent);
            System.out.println("APP READY FOR REQUESTS");

            hasRun = true;
        }
    }

}
