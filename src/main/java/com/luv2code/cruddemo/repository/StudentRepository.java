package com.luv2code.cruddemo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.luv2code.cruddemo.model.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
// Indicates that the class is a Data Access Object (DAO) component in the
// persistence layer.
// Automatic exception translation - Spring will catch any persistence related
// exceptions and rethrow them as one of Spring's unchecked data access
// exceptions.
// Directly touches the database.
public class StudentRepository implements StudentDAO {

    @PersistenceContext
    // used specifically for injecting EntityManager
    private EntityManager entityManager;

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = entityManager.createQuery("FROM Student ORDER BY lastName asc", Student.class);
        return query.getResultList();
    }

    @Override
    // Optional used to allow for causes where Student cannot be found
    public Optional<Student> findById(int id) {
        return Optional.ofNullable(entityManager.find(Student.class, id));
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

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void deleteAll() {
        // Deletes all and resets id numbering
        entityManager
                .createNativeQuery("TRUNCATE table student")
                .executeUpdate();
    }

    @Override
    @Transactional
    public void delete(int id) {
        Student student = entityManager.find(Student.class, id);
        entityManager.remove(student);
    }
}
