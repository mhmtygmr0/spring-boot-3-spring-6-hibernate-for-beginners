package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    @Value("${countries}")
    private List<String> countries;

    @Value("${languages}")
    private List<String> languages;

    @Value("${systems}")
    private List<String> systems;

    @GetMapping("/showStudentForm")
    public String showForm(Model model) {
        // create a student object
        Student student = new Student();

        // add student object to the model
        model.addAttribute("student", student);

        // add the list of countries to the model
        model.addAttribute("countries", this.countries);

        // add the list of languages to the model
        model.addAttribute("languages", this.languages);

        // add the list of systems to the model
        model.addAttribute("systems", this.systems);

        return "student-form";
    }

    @PostMapping("/processStudentForm")
    public String processForm(@ModelAttribute("student") Student student) {
        // log the input data
        System.out.println("Student: " + student.getFirstName() + " " + student.getLastName());
        System.out.println("Country: " + student.getCountry());
        System.out.println("Favorite Programing Language: " + student.getFavoriteLanguage());
        System.out.println("Favorite Operating Systems: " + student.getFavoriteSystems());
        System.out.println("---------------------------------");

        return "student-confirmation";
    }
}
