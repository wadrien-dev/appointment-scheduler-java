package com.woodna.appointmentscheduler.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Full name is required.")
    @Column(nullable = false)
    private String fullName;

    @NotBlank(message = "Email is required.")
    @Email(message = "Please enter a valid email address.")
    @Column(nullable = false)
    private String email;

    @NotNull(message = "Appointment date is required.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate appointmentDate;

    @NotNull(message = "Appointment time is required.")
    @DateTimeFormat(pattern = "HH:mm")
    @Column(nullable = false)
    private LocalTime appointmentTime;

    @NotBlank(message = "Reason is required.")
    @Column(nullable = false, length = 500)
    private String reason;

    @Column(nullable = false)
    private String status = "Scheduled";

    public Appointment() {
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}