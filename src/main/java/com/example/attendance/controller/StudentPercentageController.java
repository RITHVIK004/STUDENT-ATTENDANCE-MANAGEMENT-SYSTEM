package com.example.attendance.controller;

import com.example.attendance.model.StudentPercentage;
import com.example.attendance.service.StudentPercentageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/percentage")
@CrossOrigin("*")
public class StudentPercentageController {

    @Autowired
    private StudentPercentageService service;

    @PostMapping
    public StudentPercentage save(@RequestBody StudentPercentage data) {
        System.out.println("✅ Received Data: " +
            data.getName() + ", " +
            data.getWorkingDays() + ", " +
            data.getDaysPresent() + ", " +
            data.getPercentage());

        return service.save(data);
    }

    @GetMapping
    public List<StudentPercentage> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
