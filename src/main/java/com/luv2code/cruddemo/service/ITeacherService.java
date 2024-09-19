package com.luv2code.cruddemo.service;

import java.util.List;

import com.luv2code.cruddemo.dto.TeacherDummyDataPayload;
import com.luv2code.cruddemo.model.Teacher;

public interface ITeacherService {

    public Teacher findById(Integer id);

    public List<Teacher> findAll();

    public void createTeachers(List<TeacherDummyDataPayload> teachers);

    public void deleteTeachers();

}
