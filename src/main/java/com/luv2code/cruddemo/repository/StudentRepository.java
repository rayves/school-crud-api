package com.luv2code.cruddemo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.luv2code.cruddemo.model.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;

import org.springframework.transaction.annotation.Transactional;

@Repository
// Indicates that the class is a Data Access Object (DAO) component in the
// persistence layer.
// Automatic exception translation - Spring will catch any persistence related
// exceptions and rethrow them as one of Spring's unchecked data access
// exceptions.
// Directly touches the database.
@AllArgsConstructor
public class StudentRepository implements IStudentRepository {

    @PersistenceContext
    // used specifically for injecting EntityManager
    private EntityManager entityManager;

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = entityManager.createQuery("FROM Student ORDER BY lastName asc", Student.class);
        return query.getResultList();
    }

    @Override
    public Optional<Student> findLastStudent() {
        // * Query that sorts the database table in desc order by id and returns the
        // first
        // record
        // String jpql = "SELECT s FROM Student s ORDER BY s.id DESC";
        // TypedQuery<Student> query = entityManager.createQuery(jpql, Student.class)
        // .setMaxResults(1);
        // return Optional.ofNullable(query.getSingleResult());

        // * Query that finds and returns the record with the max id through
        // sub-querying
        String jpql = "SELECT s FROM Student s WHERE s.id = (SELECT max(s2.id) FROM Student s2)";
        TypedQuery<Student> query = entityManager.createQuery(jpql, Student.class)
                .setMaxResults(1);
        return Optional.ofNullable(query.getSingleResult());

        // Native Query for finding and returning last student
        // String sql = "SELECT * FROM student WHERE id = (SELECT max(id) FROM
        // student)";
        // List<Student> resultList = entityManager.createNativeQuery(sql,
        // Student.class)
        // .getResultList();
        // return resultList.isEmpty() ? Optional.empty() :
        // Optional.of(resultList.get(0));
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
    // Transactional annotation required as the method is performing an update
    // transaction with the database
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public void update(Student student) {
        // merge saves or updates the record - if id == 0 then save/insert otherwise
        // update
        entityManager.merge(student);
    }

    @Override
    public void deleteAll() {
        // Deletes all and resets id numbering
        entityManager
                .createNativeQuery("TRUNCATE table student")
                .executeUpdate();
    }

    @Override
    @Transactional
    public void delete(int id) {
        Optional<Student> student = this.findById(id);
        student.ifPresentOrElse(entityManager::remove,
                () -> System.out.println("Student with id " + id + " not found."));
    }
}
