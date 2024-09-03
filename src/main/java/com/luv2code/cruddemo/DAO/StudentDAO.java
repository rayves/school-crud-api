package com.luv2code.cruddemo.DAO;

import com.luv2code.cruddemo.entity.Student;

public interface StudentDAO {
    Student findById(int id);

    // Student findByEmail(String email);

    void save(Student theStudent);
}
