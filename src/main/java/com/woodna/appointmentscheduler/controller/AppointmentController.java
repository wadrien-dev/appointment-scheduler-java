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

    @GetMapping("/admin")
    public String adminDashboard(Model model) {
        model.addAttribute("totalAppointments", appointmentService.getTotalAppointments());
        model.addAttribute("scheduledAppointments", appointmentService.getScheduledAppointmentsCount());
        model.addAttribute("cancelledAppointments", appointmentService.getCancelledAppointmentsCount());
        model.addAttribute("appointments", appointmentService.getAllAppointments());
        return "admin-dashboard";
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
        model.addAttribute("keyword", "");
        model.addAttribute("status", "");
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

    @GetMapping("/appointments/search")
    public String searchAppointments(@RequestParam(required = false) String keyword,
                                     @RequestParam(required = false) String status,
                                     Model model) {
        model.addAttribute("appointments", appointmentService.searchAppointments(keyword, status));
        model.addAttribute("keyword", keyword);
        model.addAttribute("status", status);
        return "appointments";
    }
}