
# Database Schema – Student Management System

## 1. Overview

The application uses PostgreSQL as its relational database.

The database name is `edurankai_db`.

The application currently uses one main table named `students` to store student enrollment information.

## 2. Students Table

| Column Name | Data Type | Constraints | Description |
|---|---|---|---|
| id | BIGINT | Primary Key, Auto Increment | Unique student ID |
| full_name | VARCHAR | NOT NULL | Student's full name |
| email | VARCHAR | NOT NULL, UNIQUE | Student's email address |
| phone_number | VARCHAR | NOT NULL | Student's 10-digit phone number |
| course | VARCHAR | NOT NULL | Enrolled course |
| enrollment_date | DATE | NOT NULL | Date of enrollment |
| status | VARCHAR | NOT NULL | ACTIVE or INACTIVE |
| deleted | BOOLEAN | NOT NULL, DEFAULT FALSE | Indicates whether the student is soft-deleted |

## 3. Primary Key and Constraints

- `id` is the primary key and is automatically generated.
- `email` must be unique to prevent duplicate student registrations.
- Required fields cannot contain null values.
- `status` is stored as a string using the values ACTIVE and INACTIVE.
- `deleted` is used for soft deletion. Deleted records remain in the database but are excluded from normal application listings and dashboard counts.

## 4. Entity Relationship Design

The system currently contains one entity: Student.

There are no foreign-key relationships because the application uses a single table.

## 5. Database Operations

The application supports:

- Creating student records.
- Retrieving student records.
- Updating student information.
- Soft-deleting student records.
- Searching students by name or email.
- Filtering students by course and status.
- Generating dashboard statistics.

## 6. Database Technology

- Database: PostgreSQL
- ORM: Hibernate
- Persistence Framework: Spring Data JPA
