# Appointment Scheduler Java App

A full-stack web-based appointment scheduling application built with **Java**, **Spring Boot**, **Thymeleaf**, **MySQL**, **Bootstrap**, **Git**, and **GitHub**.

## Overview

This project allows users to schedule and manage appointments through a browser-based interface. It also includes an admin dashboard for viewing, editing, searching, filtering, and cancelling appointment records.

The application was developed as part of a multi-phase Software Development Life Cycle project and includes planning, analysis, design, and development documentation.

## Live Demo

[Open the live application](https://appointment-scheduler-java.onrender.com)

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
<img width="1146" height="780" alt="Screenshot 2026-05-03 175417" src="https://github.com/user-attachments/assets/a4671f76-4d4c-4af1-80a9-daddf05a29fb" />

<img width="1206" height="818" alt="Screenshot 2026-05-03 175505" src="https://github.com/user-attachments/assets/f2166083-8fd8-4cf2-b98c-874fca832ddc" />

### Find My Appointments
<img width="1138" height="630" alt="Screenshot 2026-05-03 182434" src="https://github.com/user-attachments/assets/ab16f4ae-4efb-435c-b9e2-2873177079bc" />

### Admin Login
<img width="638" height="593" alt="image" src="https://github.com/user-attachments/assets/a69c71c2-c57b-48f2-8f39-763c02bba66b" />

### Admin Dashboard
<img width="975" height="678" alt="image" src="https://github.com/user-attachments/assets/9eea4ed1-d1b9-4f99-b71d-bcc9b85139d1" />

### All Appointments
<img width="975" height="627" alt="image" src="https://github.com/user-attachments/assets/e8935c06-bc5e-4abd-859b-f0ea0690dce8" />

### Edit Appointment
<img width="975" height="714" alt="image" src="https://github.com/user-attachments/assets/d824ea71-a403-43d2-a75b-7af8254ad7f3" />

<img width="975" height="673" alt="image" src="https://github.com/user-attachments/assets/ae538218-fa91-4a63-9c6f-baa8fb986253" />

