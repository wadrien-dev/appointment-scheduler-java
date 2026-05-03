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

    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found."));
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

    public Appointment updateAppointment(Long id, Appointment updatedAppointment) {
        Appointment existingAppointment = getAppointmentById(id);

        Optional<Appointment> conflictingAppointment = appointmentRepository
                .findByAppointmentDateAndAppointmentTime(
                        updatedAppointment.getAppointmentDate(),
                        updatedAppointment.getAppointmentTime()
                );

        if (conflictingAppointment.isPresent() &&
                !conflictingAppointment.get().getId().equals(id)) {
            throw new IllegalArgumentException("That appointment slot is already taken.");
        }

        existingAppointment.setFullName(updatedAppointment.getFullName());
        existingAppointment.setEmail(updatedAppointment.getEmail());
        existingAppointment.setAppointmentDate(updatedAppointment.getAppointmentDate());
        existingAppointment.setAppointmentTime(updatedAppointment.getAppointmentTime());
        existingAppointment.setReason(updatedAppointment.getReason());
        existingAppointment.setStatus(updatedAppointment.getStatus());

        return appointmentRepository.save(existingAppointment);
    }

    public void cancelAppointment(Long id) {
        Appointment appointment = getAppointmentById(id);
        appointment.setStatus("Cancelled");
        appointmentRepository.save(appointment);
    }
}