package com.rvo.schoolcrudapi.service;

import java.util.List;

import com.rvo.schoolcrudapi.dto.StudentDummyDataPayload;
import com.rvo.schoolcrudapi.dto.StudentUpdateRequest;
import com.rvo.schoolcrudapi.model.Student;

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
