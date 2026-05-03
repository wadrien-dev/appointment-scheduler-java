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
        return appointmentRepository.findAll()
                .stream()
                .sorted((a1, a2) -> {
                    int dateCompare = a1.getAppointmentDate().compareTo(a2.getAppointmentDate());
                    if (dateCompare != 0) {
                        return dateCompare;
                    }
                    return a1.getAppointmentTime().compareTo(a2.getAppointmentTime());
                })
                .toList();
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
        existingAppointment.setPhoneNumber(updatedAppointment.getPhoneNumber());
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

    public long getTotalAppointments() {
        return appointmentRepository.count();
    }

    public long getScheduledAppointmentsCount() {
        return appointmentRepository.findAll()
                .stream()
                .filter(a -> "Scheduled".equalsIgnoreCase(a.getStatus()))
                .count();
    }

    public long getCancelledAppointmentsCount() {
        return appointmentRepository.findAll()
                .stream()
                .filter(a -> "Cancelled".equalsIgnoreCase(a.getStatus()))
                .count();
    }

    public List<Appointment> searchAppointments(String keyword, String status) {
        return appointmentRepository.findAll()
                .stream()
                .filter(appointment -> {
                    boolean matchesKeyword = (keyword == null || keyword.isBlank()) ||
                            appointment.getFullName().toLowerCase().contains(keyword.toLowerCase()) ||
                            appointment.getEmail().toLowerCase().contains(keyword.toLowerCase());

                    boolean matchesStatus = (status == null || status.isBlank()) ||
                            appointment.getStatus().equalsIgnoreCase(status);

                    return matchesKeyword && matchesStatus;
                })
                .sorted((a1, a2) -> {
                    int dateCompare = a1.getAppointmentDate().compareTo(a2.getAppointmentDate());
                    if (dateCompare != 0) {
                        return dateCompare;
                    }
                    return a1.getAppointmentTime().compareTo(a2.getAppointmentTime());
                })
                .toList();
    }
    public List<Appointment> getAppointmentsByEmailAndPhone(String email, String phoneNumber) {
        return appointmentRepository.findByEmailAndPhoneNumber(email, phoneNumber)
                .stream()
                .sorted((a1, a2) -> {
                    int dateCompare = a1.getAppointmentDate().compareTo(a2.getAppointmentDate());
                    if (dateCompare != 0) {
                        return dateCompare;
                    }
                    return a1.getAppointmentTime().compareTo(a2.getAppointmentTime());
                })
                .toList();
    }
    public boolean userOwnsAppointment(Long id, String email, String phoneNumber) {
        Appointment appointment = getAppointmentById(id);
        return appointment.getEmail().equalsIgnoreCase(email)
                && appointment.getPhoneNumber().equals(phoneNumber);
    }
}