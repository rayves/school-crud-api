package com.rvo.schoolcrudapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rvo.schoolcrudapi.dto.TeacherDummyDataPayload;
import com.rvo.schoolcrudapi.dto.TeacherDummyDataPayload.TempTeacher;
import com.rvo.schoolcrudapi.model.Teacher;
import com.rvo.schoolcrudapi.repository.ITeacherRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TeacherService implements ITeacherService {

    private ITeacherRepository teacherRepository;

    @Override
    @Transactional(readOnly = true)
    public Teacher findById(Integer id) {
        Optional<Teacher> result = teacherRepository.findById(id);

        return result.orElseThrow(() -> new RuntimeException("Teacher cannot be found"));
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
    public void createTeachers(TeacherDummyDataPayload teacherPayload) {
        System.out.println("Generating Teachers...");
        for (TempTeacher teacher : teacherPayload.getTeachers()) {
            String firstName = teacher.getFirstName();
            String lastName = teacher.getLastName();
            String email = teacher.getEmail();
            String subject = teacher.getCompany().getDepartment();
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
