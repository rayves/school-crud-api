package com.rvo.schoolcrudapi.startup;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.rvo.schoolcrudapi.enums.Role;
import com.rvo.schoolcrudapi.model.Authority;
import com.rvo.schoolcrudapi.model.User;
import com.rvo.schoolcrudapi.service.IAuthorityService;
import com.rvo.schoolcrudapi.service.IStudentService;
import com.rvo.schoolcrudapi.service.ITeacherService;
import com.rvo.schoolcrudapi.service.IUserService;

@Component
public class StartupApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    private final IStudentService studentService;
    private final ITeacherService teacherService;
    private final IAuthorityService authorityService;
    private final IUserService userService;
    private boolean hasRun = false;

    @Autowired
    public StartupApplicationListener(IStudentService studentService, ITeacherService teacherService,
            IAuthorityService authorityService, IUserService userService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.authorityService = authorityService;
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (!hasRun) {
            createAuthorities();
            createUsers();
            createStudents();
            createTeachers();

            System.out.println("APP READY FOR REQUESTS");

            hasRun = true;
        }
    }

    public void createAuthorities() {
        List<Authority> authorities = new ArrayList<>(Arrays.asList(
                new Authority(Role.ADMIN.name().toLowerCase()),
                new Authority(Role.TEACHER.name().toLowerCase()),
                new Authority(Role.STUDENT.name().toLowerCase())));
        for (Authority authority : authorities) {
            authorityService.createAuthority(authority);
        }
    }

    public void createUsers() {
        userService.createUserWithAuthorities(
                new User("admin", "admin123"),
                new ArrayList<>(Arrays.asList(Role.ADMIN.name(), Role.TEACHER.name(), Role.STUDENT.name())));

        userService.createUserWithAuthorities(
                new User("teacher", "teacher123"),
                new ArrayList<>(Arrays.asList(Role.TEACHER.name(), Role.STUDENT.name())));

        userService.createUserWithAuthorities(
                new User("student", "student123"),
                new ArrayList<>(Arrays.asList(Role.STUDENT.name())));
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
