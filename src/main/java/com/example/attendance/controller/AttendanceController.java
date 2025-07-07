package com.example.attendance.controller;

import com.example.attendance.model.Attendance;
import com.example.attendance.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.attendance.dto.AttendanceReportDTO;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService service;

    @PostMapping
    public Attendance markAttendance(@RequestBody Attendance attendance) {
        return service.save(attendance);
    }

    @GetMapping
    public List<Attendance> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
    @GetMapping("/search")
    public List<Attendance> searchAttendance(
            @RequestParam(required = false) String studentName,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        if (studentName != null && date != null) {
            return service.searchByNameAndDate(studentName, date);
        } else if (studentName != null) {
            return service.searchByName(studentName);
        } else if (date != null) {
            return service.searchByDate(date);
        } else {
            return service.getAll();
        }
    }
    @GetMapping("/report")
    public List<AttendanceReportDTO> getReport() {
        return service.getAttendanceReport();
    }

}
