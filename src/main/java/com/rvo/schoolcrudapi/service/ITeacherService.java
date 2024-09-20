package com.rvo.schoolcrudapi.service;

import java.util.List;

import com.rvo.schoolcrudapi.dto.TeacherDummyDataPayload;
import com.rvo.schoolcrudapi.model.Teacher;

public interface ITeacherService {

    public Teacher findById(Integer id);

    public List<Teacher> findAll();

    public void createTeachers(List<TeacherDummyDataPayload> teachers);

    public void deleteTeachers();

}
