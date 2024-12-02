package com.luv2code.cruddemo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "instructor")
public class Instructor {

    // annotate the class as an entity and map to db table

    // define the fields

    // annotate the fields with db column names

    // create constructors

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id")
    private InstructorDetail instructorDetail;

    public Instructor() {
    }

    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
