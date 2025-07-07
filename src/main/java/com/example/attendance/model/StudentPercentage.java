package com.example.attendance.model;

import jakarta.persistence.*;

@Entity
@Table(name = "student_percentage")
public class StudentPercentage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "working_days")
    private int workingDays;

    @Column(name = "days_present")
    private int daysPresent;

    private double percentage;

    // --- Getters & Setters ---
    public Long getId() { return id; }
    public String getName() { return name; }
    public int getWorkingDays() { return workingDays; }
    public int getDaysPresent() { return daysPresent; }
    public double getPercentage() { return percentage; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setWorkingDays(int workingDays) { this.workingDays = workingDays; }
    public void setDaysPresent(int daysPresent) { this.daysPresent = daysPresent; }
    public void setPercentage(double percentage) { this.percentage = percentage; }
}
