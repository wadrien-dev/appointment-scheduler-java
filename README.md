# Appointment Scheduler Java App

A full-stack web application for scheduling, locating, editing, and managing appointment records through a browser-based interface.

Built with **Java 21**, **Spring Boot**, **Spring Data JPA**, **Thymeleaf**, **MySQL**, **Bootstrap 5**, **Maven**.

## Live Demo

[Open the live application](https://appointment-scheduler-java.onrender.com)

**Note:** The application is hosted on Render. The first load may take a moment if the service has been inactive.

## Overview

The Appointment Scheduler Java App was developed as part of a multi-phase Software Development Life Cycle project.

The application provides user-facing appointment-management tools and an administrative dashboard for reviewing, searching, filtering, editing, and cancelling records. It demonstrates full-stack development, layered architecture, database integration, business-rule validation, and server-rendered user interfaces.

## Features

### User Features
- Schedule a new appointment
- Locate appointments using an email address and phone number
- View matching appointment records
- Edit only verified appointments
- Cancel only verified appointments

### Admin Features
- Log in through an administrative portal
- View dashboard summary cards
- Review all appointment records
- Search and filter appointment data
- Edit any appointment record
- Cancel any appointment record
- Correct user contact information without forcing unnecessary changes to other fields


## Tech Stack

### Backend
- Java 21
- Spring Boot
- Spring Data JPA
- Thymeleaf
- Maven

### Frontend
- Thymeleaf
- HTML
- CSS
- Bootstrap 5

### Databases
- MySQL for local development
- PostgreSQL for the deployed Render environment

### Deployment
- Render
- PostgreSQL production database hosted for the live application

### Development Tools
- Git
- GitHub
- IntelliJ IDEA
- draw.io

## Architecture

The application uses a layered architecture that separates request handling, business logic, data access, and presentation.

Browser
    ↓
Controller Layer
    ↓
Service Layer
    ↓
Repository Layer
    ↓
Database

- **Controller layer:** Handles browser requests and routes users to the appropriate views.
- **Service layer:** Contains business logic and appointment-management rules.
- **Repository layer:** Communicates with the MySQL database through Spring Data JPA.
- **Database layer:** Uses MySQL for local development and PostgreSQL for the deployed Render environment.
- **Presentation layer:** Uses Thymeleaf templates and Bootstrap styling to render the user interface.

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
- Home / Schedule Appointment Form
- Find My Appointments
- Admin Login
- Admin Dashboard
- All Appointments
- Edit Appointment
- Admin Edit Appointment
- Access Denied

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

## Run the Project Locally

### Prerequisites
Install the following before running the project:
- Java 21
- Maven
- MySQL
- Git

### Installation
1. Clone the repository:
   git clone https://github.com/wadrien-dev/appointment-scheduler-java.git
3. Open the project folder:
   cd appointment-scheduler-java
4. Create a MySQL database for the application.
5. Update the database configuration in:
   src/main/resources/application.properties
6. Run the application:
   mvn spring-boot:run
7. Open the application in your browser:
   http://localhost:8080

## What This Project Demonstrates

This project strengthened my experience with:
- Java backend development
- Spring Boot application structure
- Database-driven web applications
- Spring Data JPA repositories
- Layered architecture
- Business-rule validation
- Search and filtering functionality
- Server-rendered interfaces with Thymeleaf
- Responsive styling with Bootstrap
- Git and GitHub version control
- SDLC planning and technical documentation
- Application deployment

## Future Improvements
- Integrate Spring Security
- Store role-based authentication details in the database
- Add email notifications
- Add calendar integration
- Implement audit logging
- Add pagination
- Expand administrative analytics
- Add automated unit and integration tests
- Strengthen validation and exception handling
- Configure a persistent production database

## Author

### Woodna Adrien
Computer Science Graduate | Junior Software Developer | Application Support | QA Testing
