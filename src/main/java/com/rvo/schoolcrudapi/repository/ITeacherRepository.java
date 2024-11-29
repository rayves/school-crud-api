package com.rvo.schoolcrudapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.rvo.schoolcrudapi.model.Teacher;

// @RepositoryRestResource(path = "faculty")
public interface ITeacherRepository extends JpaRepository<Teacher, Integer> {

    @Modifying
    @Query(value = "TRUNCATE table teacher", nativeQuery = true)
    public void deleteAll();

}
