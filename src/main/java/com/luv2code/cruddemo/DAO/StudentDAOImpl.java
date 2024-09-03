package com.luv2code.cruddemo.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.cruddemo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
// Indicates that the class is a Data Access Object (DAO) component in the
// persistence layer.
// Automatic exception translation - Spring will catch any persistence related
// exceptions and rethrow them as one of Spring's unchecked data access
// exceptions.
public class StudentDAOImpl implements StudentDAO {

    private EntityManager entityManager;

    @Autowired
    // Autowired is optional if there is only 1 constructor
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Student findById(int id) {
        return entityManager.find(Student.class, id);
    }

    // @Override
    // public Student findByEmail(String email) {
    // return entityManager.find(Student.class, email);
    // }

    @Override
    @Transactional
    // Transactional annotation required as the method is performing an update
    // transaction with the database
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

}
