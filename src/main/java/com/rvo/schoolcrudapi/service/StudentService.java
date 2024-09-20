package com.rvo.schoolcrudapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rvo.schoolcrudapi.dto.StudentDummyDataPayload;
import com.rvo.schoolcrudapi.dto.StudentUpdateRequest;
import com.rvo.schoolcrudapi.exception.StudentNotFoundException;
import com.rvo.schoolcrudapi.model.Student;
import com.rvo.schoolcrudapi.repository.IStudentRepository;

import lombok.AllArgsConstructor;

import org.springframework.transaction.annotation.Transactional;

@Service
// Handles the business logic related the database but does not directly touch
// the database
@AllArgsConstructor
// With this AllArgsConstructor annotation there will only be 1 constructor,
// therefore no need to use @Autowired
public class StudentService implements IStudentService {

    private IStudentRepository studentRepository;

    private ObjectMapper objectMapper;

    // GET
    @Transactional(readOnly = true)
    public Student queryByStudentId(int id) {
        System.out.println("Retrieving student by id..." + id);
        return studentRepository.findById(id)
                .orElseThrow(() -> {
                    System.out.println("Student with Id " + id + " not found");
                    return new StudentNotFoundException(String.format("Student of id %d not found", id));
                });
    }

    @Transactional(readOnly = true)
    public Student queryByStudentEmail(String email) {
        System.out.println("Retrieving student by Email..." + email);
        Student student = studentRepository.findByEmail(email);
        if (student != null)
            System.out.println("Found the student " + student);
        return student;
    }

    @Transactional(readOnly = true)
    public List<Student> queryForAllStudents() {
        System.out.println("Retrieving all students...");
        List<Student> students = studentRepository.findAll();
        System.out.println("Number of Students found: " + students.size());
        for (Student student : students) {
            System.out.println(student);
        }
        return students;
    }

    // POST

    @Transactional
    public Student createStudent(Student student) {
        System.out.println("Creating new student..." + student);
        studentRepository.save(student);
        Optional<Student> newStudent = studentRepository.findLastStudent();
        return newStudent
                .orElseThrow(() -> new StudentNotFoundException(
                        String.format("Last student created $s cannot be found", student)));
    }

    @Transactional
    public void createStudents(List<StudentDummyDataPayload> students) {
        System.out.println("Creating all new students...");
        for (StudentDummyDataPayload student : students) {
            System.out.println("Creating new student object...");
            String firstName = student.getFullName().split(" ")[0].toLowerCase();
            String lastName = student.getFullName().split(" ")[1].toLowerCase();
            Student tempStudent = new Student(firstName, lastName, student.getEmail().toLowerCase());
            System.out.println("Saving new student...");
            studentRepository.save(tempStudent);
            System.out.println("Saved student. Generated Id: " + tempStudent.getId());
        }
    }

    // PUT

    @Transactional
    public Student updateStudent(int id, StudentUpdateRequest studentUpdate) {
        System.out.println("Updating student with id..." + id);
        Student existingStudent = this.queryByStudentId(id);
        try {
            objectMapper.updateValue(existingStudent, studentUpdate);
        } catch (JsonMappingException ex) {
            throw new RuntimeException("Failed to map JSON to Student object " + ex.getOriginalMessage(), ex);
        }
        studentRepository.update(existingStudent);
        System.out.println("Updated Student... " + existingStudent);
        return existingStudent;
    }

    // DELETE

    @Transactional
    public void resetStudents() {
        System.out.println("Cleaning Students Table in Database...");
        studentRepository.deleteAll();
    }

    @Transactional
    public void deleteStudent(int id) {
        Student student = this.queryByStudentId(id);
        studentRepository.delete(id);
        System.out.println("Student Deleted..." + student);
    }

}
