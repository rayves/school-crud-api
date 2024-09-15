package com.luv2code.cruddemo.startup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.luv2code.cruddemo.dto.StudentDummyDataPayload;
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

            List<StudentDummyDataPayload> response = client.get()
                    .uri("/users")
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<StudentDummyDataPayload>>() {
                    })
                    .block();

            studentService.createStudents(response);
            studentService.queryForAllStudents();
            System.out.println("APP READY FOR REQUESTS");

            hasRun = true;
        }
    }

}
