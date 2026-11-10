
# Project Report
## Student Management & Enrollment System

### 1. Introduction

The Student Management & Enrollment System is a full-stack web application developed to manage student enrollment records efficiently.

The application provides functionality to create, view, update, search, filter, and delete student records. It also provides dashboard statistics showing the total number of students and their enrollment status.

### 2. Objectives

The main objectives of this project are:

- Develop a centralized system for managing student records.
- Implement REST APIs for student management.
- Maintain student information in a PostgreSQL database.
- Provide searching and filtering functionality.
- Display student statistics through a dashboard.
- Implement validation and error handling.
- Verify application functionality through automated tests.

### 3. Technology Stack

| Component | Technology |
|---|---|
| Programming Language | Java 21 |
| Backend Framework | Spring Boot |
| Database | PostgreSQL |
| ORM | Spring Data JPA / Hibernate |
| Frontend | HTML, CSS, JavaScript |
| API Communication | REST APIs, Fetch API |
| Build Tool | Gradle |
| Testing | JUnit 5, Spring Boot Test |

### 4. System Architecture

The application follows a layered architecture:

Frontend → Controller → Service → Repository → PostgreSQL

- **Frontend:** Provides the user interface for managing student records.
- **Controller:** Handles HTTP requests and responses.
- **Service:** Implements business logic and student management operations.
- **Repository:** Handles database interactions using Spring Data JPA.
- **Database:** Stores student enrollment information.

### 5. Implemented Features

- Student registration and record management.
- Viewing individual student details.
- Updating existing student information.
- Soft deletion of student records.
- Searching students by name and email.
- Filtering students by course and status.
- Dashboard displaying total, active, and inactive student counts.
- Email uniqueness and input validation.
- Centralized exception handling with appropriate HTTP status codes.

### 6. Database Design

PostgreSQL is used to store student information in the `students` table.

The table contains student ID, full name, email, phone number, course, enrollment date, status, and a soft-delete indicator.

The email column has a unique constraint to prevent duplicate registrations.

### 7. Testing

Automated integration tests were implemented using JUnit and Spring Boot Test.

The following scenarios were tested:

1. Successful student creation.
2. Rejection of invalid email addresses.
3. Rejection of duplicate email addresses.
4. Successful student update.

All four integration test scenarios passed during development.

### 8. Conclusion

The project demonstrates the development of a full-stack application using Java, Spring Boot, PostgreSQL, and JavaScript.

It provided practical experience in REST API development, database integration, layered architecture, validation, exception handling, frontend-backend communication, and automated testing.
