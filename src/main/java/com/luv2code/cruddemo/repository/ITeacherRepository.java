package com.luv2code.cruddemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.luv2code.cruddemo.model.Teacher;

public interface ITeacherRepository extends JpaRepository<Teacher, Integer> {

    @Modifying
    @Query(value = "TRUNCATE table teacher", nativeQuery = true)
    public void deleteAll();

}
