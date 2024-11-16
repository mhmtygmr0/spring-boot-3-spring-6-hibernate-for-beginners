package com.luv2code.springboot.thymeleafdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    private String firstName;
    private String lastName;

    public Student() {
    }
}
