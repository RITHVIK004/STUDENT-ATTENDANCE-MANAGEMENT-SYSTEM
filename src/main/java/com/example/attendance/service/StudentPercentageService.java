package com.example.attendance.service;

import com.example.attendance.model.StudentPercentage;
import com.example.attendance.repository.StudentPercentageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentPercentageService {

    @Autowired
    private StudentPercentageRepository repo;

    public StudentPercentage save(StudentPercentage sp) {
        return repo.save(sp);
    }

    public List<StudentPercentage> getAll() {
        return repo.findAll();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
