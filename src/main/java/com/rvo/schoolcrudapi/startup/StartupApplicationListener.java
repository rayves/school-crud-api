package com.rvo.schoolcrudapi.startup;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.rvo.schoolcrudapi.dto.StudentDummyDataPayload;
import com.rvo.schoolcrudapi.dto.TeacherDummyDataPayload;
import com.rvo.schoolcrudapi.model.Teacher;
import com.rvo.schoolcrudapi.service.IStudentService;
import com.rvo.schoolcrudapi.service.ITeacherService;

@Component
public class StartupApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    private final IStudentService studentService;
    private final ITeacherService teacherService;
    private boolean hasRun = false;

    @Autowired
    public StartupApplicationListener(IStudentService studentService, ITeacherService teacherService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (!hasRun) {
            createStudents();
            createTeachers();

            System.out.println("APP READY FOR REQUESTS");

            hasRun = true;
        }
    }

    public void createStudents() {
        // studentService.resetStudents();

        WebClient client = WebClient.create("https://jsonplaceholder.typicode.com");

        List<StudentDummyDataPayload> response = client.get()
                .uri("/users")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<StudentDummyDataPayload>>() {
                })
                .block();

        studentService.createStudents(response);
        studentService.queryForAllStudents();
    }

    public void createTeachers() {
        WebClient client = WebClient.create("https://freetestapi.com/api/v1");

        List<TeacherDummyDataPayload> response = client.get()
                .uri("/teachers")
                .retrieve()
                .bodyToFlux(TeacherDummyDataPayload.class)
                .collectList()
                .block()
                .stream()
                .limit(5)
                .collect(Collectors.toList());

        teacherService.createTeachers(response);
        teacherService.findAll();
    }

}
