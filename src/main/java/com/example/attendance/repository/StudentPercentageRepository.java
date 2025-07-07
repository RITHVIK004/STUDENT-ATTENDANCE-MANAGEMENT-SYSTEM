package com.example.attendance.repository;

import com.example.attendance.model.StudentPercentage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentPercentageRepository extends JpaRepository<StudentPercentage, Long> {
}
