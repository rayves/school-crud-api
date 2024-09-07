package com.luv2code.cruddemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.cruddemo.model.Student;
import com.luv2code.cruddemo.repository.StudentDAO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/")
public class StudentController {

    @Autowired
    private StudentDAO studentDAO;

    @GetMapping("/")
    public String getWelcome() {
        return new String("Welcome");
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentDAO.findAll();
    }

}
