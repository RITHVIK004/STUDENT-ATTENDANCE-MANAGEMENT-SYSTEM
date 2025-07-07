package com.example.attendance.service;

import com.example.attendance.dto.AttendanceReportDTO;
import com.example.attendance.model.Attendance;
import com.example.attendance.repository.AttendanceRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AttendanceServiceTest {

    @Mock
    private AttendanceRepository attendanceRepo;

    @InjectMocks
    private AttendanceService attendanceService;

    @Test
    void testSaveAttendance() {
        Attendance att = new Attendance();
        att.setStudentName("John");
        att.setDate(LocalDate.now());
        att.setPresent(true);

        when(attendanceRepo.save(att)).thenReturn(att);

        Attendance saved = attendanceService.save(att);
        assertNotNull(saved);
        assertEquals("John", saved.getStudentName());
        assertTrue(saved.isPresent());
    }

    @Test
    void testGetAllAttendance() {
        Attendance a1 = new Attendance();
        a1.setStudentName("John");
        a1.setDate(LocalDate.now());
        a1.setPresent(true);

        Attendance a2 = new Attendance();
        a2.setStudentName("Alice");
        a2.setDate(LocalDate.now());
        a2.setPresent(false);

        when(attendanceRepo.findAll()).thenReturn(List.of(a1, a2));

        List<Attendance> result = attendanceService.getAll();
        assertEquals(2, result.size());
    }

    @Test
    void testDeleteAttendance() {
        attendanceService.delete(1L);
        verify(attendanceRepo, times(1)).deleteById(1L);
    }

    @Test
    void testSearchByName() {
        String name = "John";
        Attendance a1 = new Attendance();
        a1.setStudentName("John");
        a1.setDate(LocalDate.now());
        a1.setPresent(true);

        when(attendanceRepo.findByStudentNameContainingIgnoreCase(name)).thenReturn(List.of(a1));

        List<Attendance> result = attendanceService.searchByName(name);
        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getStudentName());
    }

    @Test
    void testSearchByDate() {
        LocalDate date = LocalDate.now();
        Attendance a1 = new Attendance();
        a1.setStudentName("John");
        a1.setDate(date);
        a1.setPresent(true);

        when(attendanceRepo.findByDate(date)).thenReturn(List.of(a1));

        List<Attendance> result = attendanceService.searchByDate(date);
        assertEquals(1, result.size());
        assertEquals(date, result.get(0).getDate());
    }

    @Test
    void testAttendanceReportGeneration() {
        LocalDate today = LocalDate.now();

        Attendance a1 = new Attendance();
        a1.setStudentName("John");
        a1.setDate(today);
        a1.setPresent(true);

        Attendance a2 = new Attendance();
        a2.setStudentName("John");
        a2.setDate(today.minusDays(1));
        a2.setPresent(false);

        Attendance a3 = new Attendance();
        a3.setStudentName("Alice");
        a3.setDate(today);
        a3.setPresent(true);

        when(attendanceRepo.findAll()).thenReturn(List.of(a1, a2, a3));

        List<AttendanceReportDTO> report = attendanceService.getAttendanceReport();

        assertEquals(2, report.size());

        AttendanceReportDTO johnReport = report.stream()
            .filter(r -> r.getStudentName().equals("John"))
            .findFirst().orElse(null);

        assertNotNull(johnReport);
        assertEquals(2, johnReport.getTotal());
        assertEquals(1, johnReport.getPresent());
        assertEquals(1, johnReport.getAbsent());
        assertEquals(50.0, johnReport.getPercentage(), 0.01);
    }
}
