package com.luv2code.cruddemo.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.cruddemo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
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
    public List<Student> findAll() {
        TypedQuery<Student> query = entityManager.createQuery("FROM Student ORDER BY lastName asc", Student.class);
        return query.getResultList();
    }

    @Override
    public Student findById(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        TypedQuery<Student> query = entityManager
                .createQuery("FROM Student WHERE lastName=:$1", Student.class)
                .setParameter("$1", lastName);
        return query.getResultList();
    }

    @Override
    public Student findByEmail(String email) {
        TypedQuery<Student> query = entityManager
                .createQuery("FROM Student WHERE email=:$1", Student.class)
                .setParameter("$1", email);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    // Transactional annotation required as the method is performing an update
    // transaction with the database
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

}
