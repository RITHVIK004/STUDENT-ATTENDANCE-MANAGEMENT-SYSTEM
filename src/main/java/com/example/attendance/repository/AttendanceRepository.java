package com.example.attendance.repository;

import com.example.attendance.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByStudentNameContainingIgnoreCase(String studentName);

    List<Attendance> findByDate(LocalDate date);

    List<Attendance> findByStudentNameContainingIgnoreCaseAndDate(String studentName, LocalDate date);
}
