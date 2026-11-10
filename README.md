# Student Management & Enrollment System

A full-stack Student Management and Enrollment System built using Java, Spring Boot, PostgreSQL, HTML, CSS, and JavaScript.

## Features

- Add, view, update, and delete student records.
- Search students by name or email.
- Filter students by course and enrollment status.
- Dashboard showing total, active, and inactive students.
- Input validation and duplicate email prevention.
- Centralized exception handling.
- Automated integration tests.

## Technology Stack

**Backend:** Java 21, Spring Boot, Spring Data JPA, Hibernate

**Frontend:** HTML, CSS, JavaScript, Fetch API

**Database:** PostgreSQL

**Testing:** JUnit 5, Spring Boot Test

**Build Tool:** Gradle

## Architecture

Frontend → Controller → Service → Repository → PostgreSQL

- Controller handles HTTP requests.
- Service contains business logic.
- Repository handles database operations.
- Entity represents database records.
- DTO transfers request data.

## Project Setup

### Prerequisites
- Java 21
- PostgreSQL
- IntelliJ IDEA

### 1. Clone the repository

```bash
git clone https://github.com/aman-sharma-cs/edurankai.git
```

### 2. Create the database

```sql
CREATE DATABASE edurankai_db;
```

### 3. Configure database credentials

Set the environment variable `DB_PASSWORD` to your PostgreSQL password.

### 4. Run the application

Run `EdurankaiApplication.java` from IntelliJ IDEA, or execute:

```bash
./gradlew bootRun
```

### 5. Open the application

http://localhost:8080

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | /api/students | Create student |
| GET | /api/students | Get all students |
| GET | /api/students/{id} | Get student by ID |
| PUT | /api/students/{id} | Update student |
| DELETE | /api/students/{id} | Delete student |
| GET | /api/students/dashboard | Dashboard statistics |

## Automated Tests

Run tests using:

```bash
./gradlew test
```

Tests cover successful creation, invalid email, duplicate email, and successful student update.

## Documentation

- Database schema: `docs/database-schema.md`
- API documentation: `docs/api-documentation.md`
- Development learnings: `LEARNINGS.md`
- Project report: `PROJECT_REPORT.md`

## Author

Developed as part of the EduRankAI Fullstack Interview Assessment.