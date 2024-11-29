package com.rvo.schoolcrudapi.repository;

import java.util.List;
import java.util.Optional;

import com.rvo.schoolcrudapi.model.Student;

public interface IStudentRepository {
    Optional<Student> findById(int id);

    Student findByEmail(String email);

    List<Student> findByLastName(String lastName);

    List<Student> findAll();

    Optional<Student> findLastStudent();

    void save(Student student);

    void update(Student student);

    void deleteAll();

    void delete(int id);
}
