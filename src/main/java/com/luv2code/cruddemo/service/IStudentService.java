package com.luv2code.cruddemo.service;

import java.util.List;

import com.luv2code.cruddemo.dto.StudentDTO;
import com.luv2code.cruddemo.model.Student;

public interface IStudentService {

    public void createStudents(List<StudentDTO> students);

    public void updateStudent(Student student);

    public Student createStudent(Student students);

    public Student queryByStudentId(int id);

    public Student queryByStudentEmail(String email);

    public List<Student> queryForAllStudents();

    public void resetStudents();
}
