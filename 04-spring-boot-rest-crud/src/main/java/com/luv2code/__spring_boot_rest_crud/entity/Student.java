package com.luv2code.__spring_boot_rest_crud.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Student {

    private String firstName;
    private String lastName;

    public Student() {
    }
}
