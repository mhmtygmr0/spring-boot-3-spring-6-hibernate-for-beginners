package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return runner -> {
            // this.createInstructor(appDAO);
            // this.findInstructor(appDAO);
            // this.deleteInstructor(appDAO);
            // this.findInstructorDetail(appDAO);
            // this.deleteInstructorDetail(appDAO);

            // this.createInstructorWithCourses(appDAO);
            // this.findInstructorWithCourses(appDAO);
            // this.findCoursesForInstructor(appDAO);
            // this.findInstructorWithCoursesJoinFetch(appDAO);
            // this.updateInstructor(appDAO);
            // this.updateCourse(appDAO);
            // this.deleteCourse(appDAO);

            // this.createCourse(appDAO);
            // this.retrieveCourseAndReviews(appDAO);
            // this.deleteCourseAndReviews(appDAO);

            // this.createCourseAndStudents(appDAO);
            // this.findCourseAndStudents(appDAO);
            // this.findStudentAndCourses(appDAO);
            // this.addMoreCoursesForStudent(appDAO);
            // this.deleteCourse(appDAO);
            // this.deleteStudent(appDAO);
        };
    }

    private void deleteStudent(AppDAO appDAO) {
        int id = 1;

        System.out.println("Deleting student id: " + id);

        appDAO.deleteStudentById(id);

        System.out.println("Done!");
    }

    private void addMoreCoursesForStudent(AppDAO appDAO) {
        int id = 2;

        Student student = appDAO.findStudentAndCoursesByCourseId(id);

        // create more courses
        Course course1 = new Course("Rubik's Cube - How to Speed Cube");
        Course course2 = new Course("Atari 2600 - Game Development");

        // add courses to student
        student.addCourse(course1);
        student.addCourse(course2);

        System.out.println("Updating student: " + student);
        System.out.println("associated courses: " + student.getCourses());

        appDAO.update(student);

        System.out.println("Done!");
    }

    private void findStudentAndCourses(AppDAO appDAO) {
        int id = 2;

        Student student = appDAO.findStudentAndCoursesByCourseId(id);

        System.out.println("Loaded student: " + student);
        System.out.println("Courses: " + student.getCourses());

        System.out.println("Done!");
    }

    private void findCourseAndStudents(AppDAO appDAO) {
        int id = 10;

        Course course = appDAO.findCourseAndStudentsByCourseId(id);

        System.out.println("Loaded course: " + course);
        System.out.println("Students: " + course.getStudents());

        System.out.println("Done!");
    }

    private void createCourseAndStudents(AppDAO appDAO) {

        // create a course
        Course newCourse = new Course("Pacman - How To Score One Million Points");

        // create the students
        Student newStudent1 = new Student("John", "Doe", "john@luv2code.com");
        Student newStudent2 = new Student("Mary", "Public", "mary@luv2code.com");

        // add students to the course
        newCourse.addStudent(newStudent1);
        newCourse.addStudent(newStudent2);

        // save the course and associated students
        System.out.println("Saving the course: " + newCourse);
        System.out.println("associated students: " + newCourse.getStudents());

        appDAO.save(newCourse);

        System.out.println("Done!");
    }

    private void deleteCourseAndReviews(AppDAO appDAO) {
        int id = 10;

        System.out.println("Deleting course id; " + id);

        appDAO.deleteCourseById(id);

        System.out.println("Done!");
    }

    private void retrieveCourseAndReviews(AppDAO appDAO) {

        // get the course and reviews
        int id = 10;
        Course course = appDAO.findCourseAndReviewsByCourseId(id);

        // print the course
        System.out.println(course);

        // print the reviews
        System.out.println(course.getReviews());

        System.out.println("Done!");
    }

    private void createCourse(AppDAO appDAO) {

        // create a course
        Course newCourse = new Course("Pacman - How To Score One Million Points");

        // add some reviews
        newCourse.addReview(new Review("Great course ... loved it!"));
        newCourse.addReview(new Review("Cool course job well done."));
        newCourse.addReview(new Review("What a dumb course, you are an idiot!"));

        // save the course ... and leverage the cascade all
        System.out.println("Saving course");
        System.out.println(newCourse);
        System.out.println(newCourse.getReviews());

        appDAO.save(newCourse); // Cascade işlemi sayesinde review'lar da kaydedilecek

        System.out.println("Done!");
    }

    private void deleteCourse(AppDAO appDAO) {
        int id = 10;

        System.out.println("Deleting course id: " + id);

        appDAO.deleteCourseById(id);

        System.out.println("Done!");
    }

    private void updateCourse(AppDAO appDAO) {
        int id = 10;

        // find the instructor
        System.out.println("Finding course id: " + id);
        Course course = appDAO.findCourseById(id);

        // update the instructor
        System.out.println("Updating course id: " + id);
        course.setTitle("Enjoy the Simple Things");

        appDAO.update(course);


        System.out.println("Done!");
    }

    private void updateInstructor(AppDAO appDAO) {
        int id = 1;

        // find the instructor
        System.out.println("Finding instructor id: " + id);
        Instructor instructor = appDAO.findInstructorById(id);

        // update the instructor
        System.out.println("Updating instructor id: " + id);
        instructor.setFirstName("TESTER");

        appDAO.update(instructor);


        System.out.println("Done!");
    }

    private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
        int id = 1;

        // find the instructor
        System.out.println("Finding instructor id: " + id);
        Instructor instructor = appDAO.findInstructorByIdJoinFetch(id);

        System.out.println("Instructor: " + instructor);
        System.out.println("The associated courses: " + instructor.getCourses());

        System.out.println("Done!");
    }

    private void findCoursesForInstructor(AppDAO appDAO) {
        int id = 1;
        System.out.println("Finding instructor id: " + id);

        Instructor tempInstructor = appDAO.findInstructorById(id);

        System.out.println("tempInstructor: " + tempInstructor);

        // find courses for instructor
        System.out.println("Finding courses for instructor id: " + id);
        List<Course> courses = appDAO.findCoursesByInstructorId(id);

        // associate the objects
        tempInstructor.setCourses(courses);

        System.out.println("the associated courses" + tempInstructor.getCourses());

        System.out.println("Done!");
    }

    private void findInstructorWithCourses(AppDAO appDAO) {
        int id = 1;
        System.out.println("Finding instructor id: " + id);

        Instructor tempInstructor = appDAO.findInstructorById(id);

        System.out.println("tempInstructor: " + tempInstructor);
        System.out.println("the associated courses: " + tempInstructor.getCourses());
    }

    private void createInstructorWithCourses(AppDAO appDAO) {
        // create the instructor
        Instructor newInstructor = new Instructor("Chad", "Darby", "darby@luv2code.com");

        // create the instructor detail
        InstructorDetail newInstructorDetail = new InstructorDetail("http:www.luv2code.com/youtube", "Luv 2 code!!!");

        // associate the objects
        newInstructor.setInstructorDetail(newInstructorDetail);

        // create some courses
        Course newCourse1 = new Course("Air Guitar - The Ultimate Guide");
        Course newCourse2 = new Course("The Pinball Masterclass");

        // add courses to instructor
        newInstructor.add(newCourse1);
        newInstructor.add(newCourse2);

        // save the instructor
        //
        // NOTE: this will ALSO save the courses
        // because of CascadeType.PERSIST
        //
        System.out.println("Saving instructor: " + newInstructor);
        System.out.println("The courses: " + newInstructor.getCourses());
        appDAO.save(newInstructor);

        System.out.println("Done!");
    }

    private void deleteInstructorDetail(AppDAO appDAO) {
        int id = 2;

        System.out.println("Deleting instructor detail id; " + id);

        appDAO.deleteInstructorDetailById(id);

        System.out.println("Done!");
    }

    private void findInstructorDetail(AppDAO appDAO) {
        int id = 2;

        InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(id);

        System.out.println("tempInstructorDetail: " + tempInstructorDetail);

        System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());

        System.out.println("Done!");
    }

    private void deleteInstructor(AppDAO appDAO) {
        int id = 1;
        System.out.println("Deleting instructor id; " + id);

        appDAO.deleteInstructorById(id);

        System.out.println("Done!");
    }

    private void findInstructor(AppDAO appDAO) {
        int id = 1;
        System.out.println("Finding instructor id: " + id);

        Instructor tempInstructor = appDAO.findInstructorById(id);

        System.out.println("tempInstructor: " + tempInstructor);
        System.out.println("the associated instructorDetail only: " + tempInstructor.getInstructorDetail());
    }

    private void createInstructor(AppDAO appDAO) {
        // create the instructor
        Instructor newInstructor = new Instructor("Madhu", "Patel", "madhu@luv2code.com");

        // create the instructor detail
        InstructorDetail newInstructorDetail = new InstructorDetail("http:www.luv2code.com/youtube", "Guitar");


        // associate the objects
        newInstructor.setInstructorDetail(newInstructorDetail);

        // save the instructor
        //
        // NOTE: this will ALSO save the details object
        // because of CascadeType.ALL
        //
        System.out.println("Saving instructor: " + newInstructor);
        appDAO.save(newInstructor);

        System.out.println("Done!");
    }
}
