package com.luv2code.cruddemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.luv2code.cruddemo.dto.TeacherDummyDataPayload;
import com.luv2code.cruddemo.model.Teacher;
import com.luv2code.cruddemo.repository.ITeacherRepository;

import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TeacherService implements ITeacherService {

    private ITeacherRepository teacherRepository;

    @Override
    @Transactional(readOnly = true)
    public Teacher findById(Integer id) {
        Optional<Teacher> result = teacherRepository.findById(id);

        Teacher teacher = null;

        if (result.isPresent()) {
            teacher = result.get();
            System.out.println(teacher);
        }

        return teacher;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Teacher> findAll() {
        System.out.println("Finding Teachers...");
        List<Teacher> teachers = teacherRepository.findAll();
        for (Teacher teacher : teachers) {
            System.out.println(teacher);
        }
        return teachers;
    }

    @Override
    @Transactional
    public void createTeachers(List<TeacherDummyDataPayload> teachers) {
        System.out.println("Generating Teachers...");
        for (TeacherDummyDataPayload teacher : teachers) {
            String firstName = teacher.getFullName().split(" ")[0];
            String lastName = teacher.getFullName().split(" ")[1];
            String email = teacher.getEmail();
            String subject = teacher.getSubjects().get(0);
            Teacher newTeacher = new Teacher(firstName, lastName, email, subject);
            teacherRepository.save(newTeacher);
        }
    }

    @Override
    @Transactional
    public void deleteTeachers() {
        teacherRepository.deleteAll();
        System.out.println("Teachers Deleted");
    }

}
