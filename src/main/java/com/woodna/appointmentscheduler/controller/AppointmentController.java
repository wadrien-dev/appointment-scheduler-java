package com.woodna.appointmentscheduler.controller;

import com.woodna.appointmentscheduler.model.Appointment;
import com.woodna.appointmentscheduler.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("appointment", new Appointment());
        return "index";
    }

    @PostMapping("/schedule")
    public String scheduleAppointment(@Valid @ModelAttribute("appointment") Appointment appointment,
                                      BindingResult result,
                                      Model model) {
        if (result.hasErrors()) {
            return "index";
        }

        try {
            appointmentService.saveAppointment(appointment);
            model.addAttribute("successMessage", "Appointment scheduled successfully.");
            model.addAttribute("appointment", new Appointment());
        } catch (IllegalArgumentException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }

        return "index";
    }

    @GetMapping("/appointments")
    public String viewAppointments(Model model) {
        model.addAttribute("appointments", appointmentService.getAllAppointments());
        return "appointments";
    }

    @GetMapping("/appointments/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("appointment", appointmentService.getAppointmentById(id));
        return "edit-appointment";
    }

    @PostMapping("/appointments/update/{id}")
    public String updateAppointment(@PathVariable Long id,
                                    @Valid @ModelAttribute("appointment") Appointment appointment,
                                    BindingResult result,
                                    Model model) {
        if (result.hasErrors()) {
            return "edit-appointment";
        }

        try {
            appointmentService.updateAppointment(id, appointment);
            return "redirect:/appointments";
        } catch (IllegalArgumentException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "edit-appointment";
        }
    }

    @PostMapping("/appointments/cancel/{id}")
    public String cancelAppointment(@PathVariable Long id) {
        appointmentService.cancelAppointment(id);
        return "redirect:/appointments";
    }
}