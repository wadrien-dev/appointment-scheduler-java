package com.woodna.appointmentscheduler.controller;

import com.woodna.appointmentscheduler.model.Appointment;
import com.woodna.appointmentscheduler.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

@Controller
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("isAdmin", session.getAttribute("isAdmin"));
        return "index";
    }

    @GetMapping("/admin")
    public String adminDashboard(Model model, HttpSession session) {
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");

        if (isAdmin == null || !isAdmin) {
            model.addAttribute("errorMessage", "Admin login is required.");
            return "access-denied";
        }

        model.addAttribute("totalAppointments", appointmentService.getTotalAppointments());
        model.addAttribute("scheduledAppointments", appointmentService.getScheduledAppointmentsCount());
        model.addAttribute("cancelledAppointments", appointmentService.getCancelledAppointmentsCount());
        model.addAttribute("appointments", appointmentService.getAllAppointments());
        model.addAttribute("isAdmin", isAdmin);
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
    public String viewAppointments(Model model, HttpSession session) {
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");

        if (isAdmin == null || !isAdmin) {
            model.addAttribute("errorMessage", "Admin login is required to view all appointments.");
            return "access-denied";
        }

        model.addAttribute("appointments", appointmentService.getAllAppointments());
        model.addAttribute("keyword", "");
        model.addAttribute("status", "");
        model.addAttribute("isAdmin", isAdmin);
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
                                     Model model,
                                     HttpSession session) {
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");

        if (isAdmin == null || !isAdmin) {
            model.addAttribute("errorMessage", "Admin login is required to search all appointments.");
            return "access-denied";
        }

        model.addAttribute("appointments", appointmentService.searchAppointments(keyword, status));
        model.addAttribute("keyword", keyword);
        model.addAttribute("status", status);
        model.addAttribute("isAdmin", isAdmin);
        return "appointments";
    }

    @GetMapping("/my-appointments")
    public String showMyAppointmentsLookupForm() {
        return "my-appointments-lookup";
    }

    @PostMapping("/my-appointments")
    public String findMyAppointments(@RequestParam String email,
                                     @RequestParam String phoneNumber,
                                     Model model,
                                     HttpSession session) {
        session.setAttribute("verifiedEmail", email);
        session.setAttribute("verifiedPhoneNumber", phoneNumber);

        model.addAttribute("appointments", appointmentService.getAppointmentsByEmailAndPhone(email, phoneNumber));
        model.addAttribute("email", email);
        model.addAttribute("phoneNumber", phoneNumber);
        model.addAttribute("isAdmin", session.getAttribute("isAdmin"));
        return "my-appointments";
    }

    @GetMapping("/my-appointments/edit/{id}")
    public String showMyAppointmentEditForm(@PathVariable Long id,
                                            Model model,
                                            HttpSession session) {
        String verifiedEmail = (String) session.getAttribute("verifiedEmail");
        String verifiedPhoneNumber = (String) session.getAttribute("verifiedPhoneNumber");

        if (verifiedEmail == null || verifiedPhoneNumber == null ||
                !appointmentService.userOwnsAppointment(id, verifiedEmail, verifiedPhoneNumber)) {
            model.addAttribute("errorMessage", "You are not authorized to edit this appointment.");
            return "access-denied";
        }

        model.addAttribute("appointment", appointmentService.getAppointmentById(id));
        return "edit-appointment";
    }

    @PostMapping("/my-appointments/update/{id}")
    public String updateMyAppointment(@PathVariable Long id,
                                      @Valid @ModelAttribute("appointment") Appointment appointment,
                                      BindingResult result,
                                      Model model,
                                      HttpSession session) {
        String verifiedEmail = (String) session.getAttribute("verifiedEmail");
        String verifiedPhoneNumber = (String) session.getAttribute("verifiedPhoneNumber");

        if (verifiedEmail == null || verifiedPhoneNumber == null ||
                !appointmentService.userOwnsAppointment(id, verifiedEmail, verifiedPhoneNumber)) {
            model.addAttribute("errorMessage", "You are not authorized to update this appointment.");
            return "access-denied";
        }

        if (result.hasErrors()) {
            return "edit-appointment";
        }

        try {
            appointmentService.updateAppointment(id, appointment);
            return "redirect:/my-appointments";
        } catch (IllegalArgumentException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "edit-appointment";
        }
    }

    @PostMapping("/my-appointments/cancel/{id}")
    public String cancelMyAppointment(@PathVariable Long id,
                                      Model model,
                                      HttpSession session) {
        String verifiedEmail = (String) session.getAttribute("verifiedEmail");
        String verifiedPhoneNumber = (String) session.getAttribute("verifiedPhoneNumber");

        if (verifiedEmail == null || verifiedPhoneNumber == null ||
                !appointmentService.userOwnsAppointment(id, verifiedEmail, verifiedPhoneNumber)) {
            model.addAttribute("errorMessage", "You are not authorized to cancel this appointment.");
            return "access-denied";
        }

        appointmentService.cancelAppointment(id);
        return "redirect:/my-appointments";
    }

    @GetMapping("/admin/appointments/edit/{id}")
    public String showAdminEditForm(@PathVariable Long id, Model model, HttpSession session) {
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");

        if (isAdmin == null || !isAdmin) {
            model.addAttribute("errorMessage", "Admin login is required.");
            return "access-denied";
        }

        model.addAttribute("appointment", appointmentService.getAppointmentById(id));
        return "admin-edit-appointment";
    }

    @PostMapping("/admin/appointments/update/{id}")
    public String updateAppointmentAsAdmin(@PathVariable Long id,
                                           @Valid @ModelAttribute("appointment") Appointment appointment,
                                           BindingResult result,
                                           Model model,
                                           HttpSession session) {
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");

        if (isAdmin == null || !isAdmin) {
            model.addAttribute("errorMessage", "Admin login is required.");
            return "access-denied";
        }

        if (result.hasErrors()) {
            return "admin-edit-appointment";
        }

        try {
            appointmentService.updateAppointment(id, appointment);
            return "redirect:/admin";
        } catch (IllegalArgumentException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "admin-edit-appointment";
        }
    }

    @PostMapping("/admin/appointments/cancel/{id}")
    public String cancelAppointmentAsAdmin(@PathVariable Long id,
                                           Model model,
                                           HttpSession session) {
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");

        if (isAdmin == null || !isAdmin) {
            model.addAttribute("errorMessage", "Admin login is required.");
            return "access-denied";
        }

        appointmentService.cancelAppointment(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin-login")
    public String showAdminLoginPage(HttpSession session) {
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");

        if (isAdmin != null && isAdmin) {
            return "redirect:/admin";
        }

        return "admin-login";
    }

    @PostMapping("/admin-login")
    public String processAdminLogin(@RequestParam String email,
                                    @RequestParam String password,
                                    Model model,
                                    HttpSession session) {
        String adminEmail = "woody_admin1@appointmentscheduler.com";
        String adminPassword = "Admin123!";

        if (email.equalsIgnoreCase(adminEmail) && password.equals(adminPassword)) {
            session.setAttribute("isAdmin", true);
            return "redirect:/admin";
        }

        model.addAttribute("errorMessage", "Invalid admin credentials.");
        return "admin-login";
    }

    @GetMapping("/admin/logout")
    public String adminLogout(HttpSession session) {
        session.removeAttribute("isAdmin");
        return "redirect:/";
    }
}