package com.example.attendance.service;

import com.example.attendance.model.Student;
import com.example.attendance.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void testSaveStudent() {
        Student student = new Student();
        student.setName("John");
        student.setRollNumber("A101");

        when(studentRepository.save(student)).thenReturn(student);

        Student saved = studentService.save(student);
        assertNotNull(saved);
        assertEquals("John", saved.getName());
        assertEquals("A101", saved.getRollNumber());
    }

    @Test
    void testGetAllStudents() {
        Student s1 = new Student();
        s1.setName("John");
        s1.setRollNumber("A101");

        Student s2 = new Student();
        s2.setName("Alice");
        s2.setRollNumber("A102");

        List<Student> students = Arrays.asList(s1, s2);

        when(studentRepository.findAll()).thenReturn(students);

        List<Student> result = studentService.getAll();
        assertEquals(2, result.size());
        assertEquals("Alice", result.get(1).getName());
    }

    @Test
    void testDeleteStudent() {
        Long id = 1L;
        studentService.delete(id);
        verify(studentRepository, times(1)).deleteById(id);
    }
}
