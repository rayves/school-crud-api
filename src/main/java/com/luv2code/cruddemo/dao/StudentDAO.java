package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;

public interface StudentDAO {
    Student findById(int id);

    void save(Student theStudent);
}
