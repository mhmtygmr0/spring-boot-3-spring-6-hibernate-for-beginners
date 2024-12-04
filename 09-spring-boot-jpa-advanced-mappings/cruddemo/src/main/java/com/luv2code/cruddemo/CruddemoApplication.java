package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
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
            this.updateInstructor(appDAO);
        };
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
        Instructor newInstructor =
                new Instructor("Chad", "Darby", "darby@luv2code.com");

        // create the instructor detail
        InstructorDetail newInstructorDetail =
                new InstructorDetail("http:www.luv2code.com/youtube", "Luv 2 code!!!");

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
        int id = 4;
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
