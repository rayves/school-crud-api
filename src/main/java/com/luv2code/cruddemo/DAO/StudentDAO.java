package com.luv2code.cruddemo.DAO;

import java.util.List;

import com.luv2code.cruddemo.entity.Student;

public interface StudentDAO {
    Student findById(int id);

    Student findByEmail(String email);

    List<Student> findByLastName(String lastName);

    List<Student> findAll();

    void save(Student theStudent);
}
