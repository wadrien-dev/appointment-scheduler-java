# Appointment Scheduler Java App

A full-stack web-based appointment scheduling application built with **Java**, **Spring Boot**, **Thymeleaf**, **MySQL**, **Bootstrap**, **Git**, and **GitHub**.

## Overview

This project allows users to schedule and manage appointments through a browser-based interface. It also includes an admin dashboard for viewing, editing, searching, filtering, and cancelling appointment records.

The application was developed as part of a multi-phase Software Development Life Cycle project and includes planning, analysis, design, and development documentation.

## Features

### User Features
- Schedule a new appointment
- Find appointments using email and phone number
- Edit only verified appointments
- Cancel only verified appointments

### Admin Features
- Admin login
- Admin dashboard with appointment summary cards
- View all appointments
- Search and filter appointments
- Edit any appointment
- Cancel any appointment
- Correct user contact information without forcing unnecessary record changes

## Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- Thymeleaf
- MySQL
- Bootstrap 5
- Maven
- Git / GitHub
- IntelliJ IDEA
- draw.io

## Architecture

The application uses a layered architecture:

- **Controller layer** for request handling
- **Service layer** for business logic
- **Repository layer** for database access
- **MySQL database** for appointment storage
- **Thymeleaf templates** for server-rendered UI

## Project Structure

```text
src/main/java/com/user/appointmentscheduler
    controller
    model
    repository
    service

src/main/resources
    templates
    static/css
    application.properties
```
### Key Screens
- Home / Schedule Appointment
- Find My Appointments
- Admin Login
- Admin Dashboard
- All Appointments
- Edit Appointment
- Admin Edit Appointment
- Access Denied

## Future Improvements
- Spring Security integration
- role-based authentication from a database 
- email notifications
- calendar integration
- deployment with persistent production database
- audit logging
- pagination and stronger admin analytics

## Screenshots
### Home Page
### Find My Appointments
### Admin Login
### Admin Dashboard
### All Appointments
### Edit Appointment
