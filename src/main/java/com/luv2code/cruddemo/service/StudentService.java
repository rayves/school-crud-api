package com.luv2code.cruddemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.cruddemo.dto.StudentDTO;
import com.luv2code.cruddemo.exception.StudentNotFoundException;
import com.luv2code.cruddemo.model.Student;
import com.luv2code.cruddemo.repository.StudentRepository;

@Service
// Handles the business logic related the database but does not directly touch
// the database
public class StudentService implements IStudentService {

    @Autowired
    private StudentRepository studentRepository;

    public void createStudents(List<StudentDTO> students) {
        System.out.println("Creating all new students...");
        for (StudentDTO student : students) {
            System.out.println("Creating new student object...");
            String firstName = student.getFullName().split(" ")[0].toLowerCase();
            String lastName = student.getFullName().split(" ")[1].toLowerCase();
            Student tempStudent = new Student(firstName, lastName, student.getEmail().toLowerCase());
            System.out.println("Saving new student...");
            studentRepository.save(tempStudent);
            System.out.println("Saved student. Generated Id: " + tempStudent.getId());
        }
    }

    public void updateStudent(Student student) {
        System.out.println("Updating student..." + student);
        student.setLastName("TEST");
        studentRepository.update(student);
        System.out.println("Updated Student... " + student);
    }

    public Student queryByStudentId(int id) {
        System.out.println("Retrieving student by id..." + id);
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(String.format("Student of id %d not found", id)));
    }

    public Student queryByStudentEmail(String email) {
        System.out.println("Retrieving student by Email..." + email);
        Student student = studentRepository.findByEmail(email);
        if (student != null)
            System.out.println("Found the student " + student);
        return student;
    }

    public List<Student> queryForAllStudents() {
        System.out.println("Retrieving all students...");
        List<Student> students = studentRepository.findAll();
        for (Student student : students) {
            System.out.println(student);
        }
        return students;
    }

    public void resetStudents() {
        System.out.println("Cleaning Students Table in Database...");
        studentRepository.deleteAll();
    }

}
