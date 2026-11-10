
package com.example.edurankai;

import com.example.edurankai.dto.StudentRequest;
import com.example.edurankai.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
class StudentIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldCreateStudentSuccessfully() {

        StudentRequest request = new StudentRequest();

        request.setFullName("Test Student");
        request.setEmail("test" + UUID.randomUUID() + "@example.com");
        request.setPhoneNumber("9876543210");
        request.setCourse("Java");
        request.setEnrollmentDate(LocalDate.now());
        request.setStatus(Student.Status.ACTIVE);

        ResponseEntity<Student> response =
                restTemplate.postForEntity(
                        "/api/students",
                        request,
                        Student.class
                );

        assertEquals(201, response.getStatusCode().value());

        assertNotNull(response.getBody());
        assertEquals("Test Student", response.getBody().getFullName());

        // Clean up the test record
        restTemplate.delete("/api/students/" + response.getBody().getId());
    }
    @Test
    void shouldRejectInvalidEmail() {

        StudentRequest request = new StudentRequest();

        request.setFullName("Test Student");
        request.setEmail("invalid-email");
        request.setPhoneNumber("9876543210");
        request.setCourse("Java");
        request.setEnrollmentDate(LocalDate.now());
        request.setStatus(Student.Status.ACTIVE);

        ResponseEntity<String> response =
                restTemplate.postForEntity(
                        "/api/students",
                        request,
                        String.class
                );

        assertEquals(400, response.getStatusCode().value());
    }
    @Test
    void shouldRejectDuplicateEmail() {

        String email = "duplicate" + UUID.randomUUID() + "@example.com";

        StudentRequest firstRequest = new StudentRequest();
        firstRequest.setFullName("First Student");
        firstRequest.setEmail(email);
        firstRequest.setPhoneNumber("9876543210");
        firstRequest.setCourse("Java");
        firstRequest.setEnrollmentDate(LocalDate.now());
        firstRequest.setStatus(Student.Status.ACTIVE);

        ResponseEntity<Student> firstResponse =
                restTemplate.postForEntity("/api/students", firstRequest, Student.class);

        assertEquals(201, firstResponse.getStatusCode().value());

        StudentRequest duplicateRequest = new StudentRequest();
        duplicateRequest.setFullName("Second Student");
        duplicateRequest.setEmail(email);
        duplicateRequest.setPhoneNumber("9876543211");
        duplicateRequest.setCourse("Python");
        duplicateRequest.setEnrollmentDate(LocalDate.now());
        duplicateRequest.setStatus(Student.Status.ACTIVE);

        ResponseEntity<String> duplicateResponse =
                restTemplate.postForEntity("/api/students", duplicateRequest, String.class);

        assertEquals(409, duplicateResponse.getStatusCode().value());

        // Clean up the test record
        restTemplate.delete("/api/students/" + firstResponse.getBody().getId());
    }
    @Test
    void shouldUpdateStudentSuccessfully() {

        StudentRequest request = new StudentRequest();
        request.setFullName("Original Student");
        request.setEmail("update" + UUID.randomUUID() + "@example.com");
        request.setPhoneNumber("9876543210");
        request.setCourse("Java");
        request.setEnrollmentDate(LocalDate.now());
        request.setStatus(Student.Status.ACTIVE);

        ResponseEntity<Student> createResponse =
                restTemplate.postForEntity("/api/students", request, Student.class);

        assertEquals(201, createResponse.getStatusCode().value());

        Long studentId = createResponse.getBody().getId();

        request.setFullName("Updated Student");

        restTemplate.put("/api/students/" + studentId, request);

        ResponseEntity<Student> updateResponse =
                restTemplate.getForEntity(
                        "/api/students/" + studentId, Student.class);

        assertEquals(200, updateResponse.getStatusCode().value());
        assertEquals("Updated Student",
                updateResponse.getBody().getFullName());

        // Clean up
        restTemplate.delete("/api/students/" + studentId);
    }
}
