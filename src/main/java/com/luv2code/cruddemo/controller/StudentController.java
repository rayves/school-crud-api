package com.luv2code.cruddemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.luv2code.cruddemo.dto.StudentUpdateRequest;
import com.luv2code.cruddemo.exception.StudentErrorResponse;
import com.luv2code.cruddemo.exception.StudentNotFoundException;
import com.luv2code.cruddemo.model.Student;
import com.luv2code.cruddemo.service.IStudentService;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/")
public class StudentController {

    @Autowired
    private IStudentService studentService;

    // GET

    @GetMapping("/")
    public String getWelcome() {
        return new String("Welcome");
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentService.queryForAllStudents();
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Student student = studentService.queryByStudentId(id);
        return ResponseEntity.ok().body(student);
    }

    // POST

    @PostMapping("/students")
    public ResponseEntity<Student> createNewStudent(@RequestBody Student student) {
        Student newStudent = studentService.createStudent(student);
        return ResponseEntity.ok().body(newStudent);
    }

    // PUT
    @PutMapping("students/{id}")
    public ResponseEntity<Student> putStudent(@PathVariable int id, @RequestBody StudentUpdateRequest studentUpdate) {
        Student updatedStudent = studentService.updateStudent(id, studentUpdate);
        return ResponseEntity.ok().body(updatedStudent);
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<StudentErrorResponse> handleStudentNotFoundException(StudentNotFoundException ex) {

        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

}
