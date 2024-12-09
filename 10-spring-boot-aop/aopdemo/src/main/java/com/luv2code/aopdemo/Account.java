package com.luv2code.aopdemo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account {

    private String name;
    private String level;

    public Account() {
    }
}
