package com.example.attendance.dto;

public class AttendanceReportDTO {

    private String studentName;
    private long total;
    private long present;
    private long absent;
    private double percentage;

    public AttendanceReportDTO(String studentName, long total, long present) {
        this.studentName = studentName;
        this.total = total;
        this.present = present;
        this.absent = total - present;
        this.percentage = (total == 0) ? 0 : ((double) present / total) * 100;
    }

    // Getters & Setters
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public long getTotal() { return total; }
    public void setTotal(long total) { this.total = total; }

    public long getPresent() { return present; }
    public void setPresent(long present) { this.present = present; }

    public long getAbsent() { return absent; }
    public void setAbsent(long absent) { this.absent = absent; }

    public double getPercentage() { return percentage; }
    public void setPercentage(double percentage) { this.percentage = percentage; }
}
