package com.luv2code.cruddemo.repository;

import java.util.List;
import java.util.Optional;

import com.luv2code.cruddemo.model.Student;

public interface IStudentRepository {
    Optional<Student> findById(int id);

    Student findByEmail(String email);

    List<Student> findByLastName(String lastName);

    List<Student> findAll();

    void save(Student student);

    void update(Student student);

    void deleteAll();

    void delete(int id);
}
