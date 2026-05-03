package com.woodna.appointmentscheduler.service;

import com.woodna.appointmentscheduler.model.Appointment;
import com.woodna.appointmentscheduler.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment saveAppointment(Appointment appointment) {
        Optional<Appointment> existing = appointmentRepository
                .findByAppointmentDateAndAppointmentTime(
                        appointment.getAppointmentDate(),
                        appointment.getAppointmentTime()
                );

        if (existing.isPresent()) {
            throw new IllegalArgumentException("That appointment slot is already taken.");
        }

        return appointmentRepository.save(appointment);
    }
}