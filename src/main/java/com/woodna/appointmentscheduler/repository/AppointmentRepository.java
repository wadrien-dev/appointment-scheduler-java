package com.woodna.appointmentscheduler.repository;

import com.woodna.appointmentscheduler.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Optional<Appointment> findByAppointmentDateAndAppointmentTime(LocalDate appointmentDate, LocalTime appointmentTime);
}