package com.example.attendance.service;

import com.example.attendance.model.Attendance;
import com.example.attendance.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.util.List;
import com.example.attendance.dto.AttendanceReportDTO;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository repo;

    public Attendance save(Attendance attendance) {
        return repo.save(attendance);
    }

    public List<Attendance> getAll() {
        return repo.findAll();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
    public List<Attendance> searchByName(String name) {
        return repo.findByStudentNameContainingIgnoreCase(name);
    }

    public List<Attendance> searchByDate(LocalDate date) {
        return repo.findByDate(date);
    }

    public List<Attendance> searchByNameAndDate(String name, LocalDate date) {
        return repo.findByStudentNameContainingIgnoreCaseAndDate(name, date);
    }
    public List<AttendanceReportDTO> getAttendanceReport() {
        List<Attendance> all = repo.findAll();

        Map<String, List<Attendance>> grouped = all.stream()
            .collect(Collectors.groupingBy(Attendance::getStudentName));

        List<AttendanceReportDTO> reports = new ArrayList<>();

        for (Map.Entry<String, List<Attendance>> entry : grouped.entrySet()) {
            String name = entry.getKey();
            List<Attendance> list = entry.getValue();

            long total = list.size();
            long present = list.stream().filter(Attendance::isPresent).count();

            reports.add(new AttendanceReportDTO(name, total, present));
        }

        return reports;
    }

}
