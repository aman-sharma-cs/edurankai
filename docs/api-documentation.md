
# API Documentation – Student Management System

## 1. Base URL

http://localhost:8080/api/students

## 2. Create Student

- **Method:** POST
- **Endpoint:** `/api/students`
- **Description:** Creates a new student record.

### Request Body

```json
{
  "fullName": "Aman Sharma",
  "email": "aman@example.com",
  "phoneNumber": "9876543210",
  "course": "Java",
  "enrollmentDate": "2026-11-10",
  "status": "ACTIVE"
}
```

### Success Response
- Status: `201 Created`
- Returns the newly created student.

## 3. Get All Students

- **Method:** GET
- **Endpoint:** `/api/students`
- **Description:** Retrieves all non-deleted students.

### Optional Query Parameters

| Parameter | Description |
|---|---|
| search | Searches by student name or email |
| course | Filters by course |
| status | Filters by ACTIVE or INACTIVE |

### Example

`GET /api/students?search=aman&status=ACTIVE`

### Success Response
- Status: `200 OK`
- Returns a list of students.

## 4. Get Student By ID

- **Method:** GET
- **Endpoint:** `/api/students/{id}`
- **Description:** Retrieves a student using their ID.

### Example

`GET /api/students/1`

### Success Response
- Status: `200 OK`

## 5. Update Student

- **Method:** PUT
- **Endpoint:** `/api/students/{id}`
- **Description:** Updates an existing student's information.

### Request Body

Uses the same JSON structure as Create Student.

### Success Response
- Status: `200 OK`
- Returns the updated student.

## 6. Delete Student

- **Method:** DELETE
- **Endpoint:** `/api/students/{id}`
- **Description:** Soft-deletes a student record.

### Success Response
- Status: `204 No Content`

## 7. Dashboard Statistics

- **Method:** GET
- **Endpoint:** `/api/students/dashboard`
- **Description:** Retrieves total, active, and inactive student counts.

### Example Response

```json
{
  "totalStudents": 10,
  "activeStudents": 7,
  "inactiveStudents": 3
}
```

## 8. Error Handling

| HTTP Status | Meaning |
|---|---|
| 400 Bad Request | Invalid input or validation failure |
| 404 Not Found | Student does not exist |
| 409 Conflict | Duplicate email address |

Errors are returned in a consistent JSON format.

### Example Error Response

```json
{
  "error": "Email already exists"
}
```

## 9. Technologies

- Java
- Spring Boot
- Spring Data JPA
- PostgreSQL
- REST APIs
