package com.example.attendance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
    @GetMapping("/students")
    public String showStudentPage() {
        return "student";
    }
    @GetMapping("/home")
    public String showHomePage() {
        return "home"; 
    }

    @GetMapping("/index")
    public String showIndexPage() {
        return "index";
    }

}
