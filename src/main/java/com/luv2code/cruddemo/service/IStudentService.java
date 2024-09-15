package com.luv2code.cruddemo.service;

import java.util.List;

import com.luv2code.cruddemo.dto.StudentDummyDataPayload;
import com.luv2code.cruddemo.dto.StudentUpdateRequest;
import com.luv2code.cruddemo.model.Student;

public interface IStudentService {

    public void createStudents(List<StudentDummyDataPayload> students);

    public Student updateStudent(int id, StudentUpdateRequest studentUpdate);

    public Student createStudent(Student students);

    public Student queryByStudentId(int id);

    public Student queryByStudentEmail(String email);

    public List<Student> queryForAllStudents();

    public void resetStudents();

    public void deleteStudent(int id);
}
