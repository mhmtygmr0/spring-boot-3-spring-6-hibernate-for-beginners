package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO {

    private EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        this.entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return this.entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor instructor = this.entityManager.find(Instructor.class, id);

        List<Course> courses = instructor.getCourses();

        // break associations of all courses for instructor
        for (Course course : courses) {
            course.setInstructor(null);
        }

        this.entityManager.remove(instructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return this.entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        InstructorDetail instructorDetail = this.entityManager.find(InstructorDetail.class, id);
        instructorDetail.getInstructor().setInstructorDetail(null);
        this.entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {
        // create query
        TypedQuery<Course> query = this.entityManager.createQuery("FROM Course WHERE instructor.id = :data", Course.class);

        query.setParameter("data", id);

        // execute query
        List<Course> courses = query.getResultList();

        return courses;
    }

    @Override
    public Course findCourseById(int id) {
        return this.entityManager.find(Course.class, id);
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {

        // create query
        TypedQuery<Instructor> query = this.entityManager.createQuery("SELECT i FROM Instructor i " + "JOIN FETCH i.courses " + "JOIN FETCH i.instructorDetail " + "WHERE i.id = :data", Instructor.class);

        query.setParameter("data", id);

        // execute query
        Instructor instructor = query.getSingleResult();

        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        this.entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void update(Course course) {
        this.entityManager.merge(course);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
        // retrieve the course
        Course course = this.entityManager.find(Course.class, id);

        // delete the course
        this.entityManager.remove(course);
    }

    @Override
    @Transactional
    public void save(Course course) {
        this.entityManager.persist(course);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int id) {

        // create query
        TypedQuery<Course> query = this.entityManager.createQuery(
                "SELECT c FROM Course c "
                        + "JOIN FETCH c.reviews "
                        + "WHERE c.id = :data", Course.class
        );

        query.setParameter("data", id);

        // execute query
        Course course = query.getSingleResult();

        return course;
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int id) {

        // create query
        TypedQuery<Course> query = this.entityManager.createQuery(
                "SELECT c FROM Course c "
                        + "JOIN FETCH c.students "
                        + "WHERE c.id = :data", Course.class
        );

        query.setParameter("data", id);

        // execute query
        Course course = query.getSingleResult();

        return course;
    }

    @Override
    public Student findStudentAndCoursesByCourseId(int id) {

        // create query
        TypedQuery<Student> query = this.entityManager.createQuery(
                "SELECT s FROM Student s "
                        + "JOIN FETCH s.courses "
                        + "WHERE s.id = :data", Student.class
        );

        query.setParameter("data", id);

        // execute query
        Student student = query.getSingleResult();

        return student;
    }

    @Override
    @Transactional
    public void update(Student student) {
        this.entityManager.merge(student);
    }

    @Override
    @Transactional
    public void deleteStudentById(int id) {
        Student student = this.entityManager.find(Student.class, id);
        this.entityManager.remove(student);
    }
}
