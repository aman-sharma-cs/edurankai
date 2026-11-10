
# Student Management & Enrollment System

A full-stack Student Management and Enrollment System developed using Java, Spring Boot, PostgreSQL, HTML, CSS, and JavaScript.

The application allows users to manage student enrollment records, search and filter students, and view dashboard statistics.

## Features

- Add new student records.
- View all students.
- View individual student details.
- Update student information.
- Delete students using soft deletion.
- Search students by name or email.
- Filter students by course and enrollment status.
- Dashboard displaying total, active, and inactive students.
- Input validation and centralized exception handling.
- Unique email validation.
- Automated integration tests.

## Technology Stack

**Backend**
- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- REST APIs

**Frontend**
- HTML
- CSS
- JavaScript
- Fetch API

**Database**
- PostgreSQL

**Testing**
- JUnit 5
- Spring Boot Test

**Build Tool**
- Gradle

## Project Architecture

The application follows a layered architecture:

Frontend → Controller → Service → Repository → PostgreSQL

- Controller: Handles incoming HTTP requests.
- Service: Contains business logic and validation rules.
- Repository: Performs database operations.
- Entity: Represents the database structure.
- DTO: Transfers request data between frontend and backend.

## Project Structure

```text
edurankai/
├── src/
│   ├── main/
│   │   ├── java/com/example/edurankai/
│   │   │   ├── controller/
│   │   │   ├── service/
│   │   │   ├── repository/
│   │   │   ├── entity/
│   │   │   ├── dto/
│   │   │   └── exception/
│   │   └── resources/
│   │       ├── static/
│   │       │   ├── index.html
│   │       │   ├── style.css
│   │       │   └── script.js
│   │       └── application.properties
│   └── test/
├── docs/
│   ├── database-schema.md
│   └── api-documentation.md
├── LEARNINGS.md
├── README.md
└── build.gradle
```

## Setup and Installation

### Prerequisites

- Java 21
- PostgreSQL
- IntelliJ IDEA or another Java IDE
- Git

### 1. Clone the Repository

```bash
git clone https://github.com/aman-sharma-cs/edurankai.git```

### 2. Create the Database

Create a PostgreSQL database named:

```sql
CREATE DATABASE edurankai_db;
```

### 3. Configure Database Credentials

Set the following environment variable:

```text
DB_PASSWORD=12345
```

The application reads the database password from this environment variable.

### 4. Run the Application

Open the project in IntelliJ IDEA and run the main Spring Boot application class:

`EdurankaiApplication.java`

Alternatively, run:

```bash
./gradlew bootRun
```

### 5. Open the Application

Visit:

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

## Automated Testing

Run the automated tests using:

```bash
./gradlew test
```

The integration tests cover:

- Successful student creation.
- Invalid email validation.
- Duplicate email rejection.
- Successful student update.

## Documentation

Additional project documentation is available in the `docs` folder:

- Database schema: `docs/database-schema.md`
- API documentation: `docs/api-documentation.md`
- Development learnings: `LEARNINGS.md`

## Author

Developed as part of the EduRankAI Fullstack Interview Assessment.
