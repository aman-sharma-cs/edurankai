
package com.example.edurankai.controller;

import com.example.edurankai.dto.DashboardResponse;
import com.example.edurankai.dto.StudentRequest;
import com.example.edurankai.entity.Student;
import jakarta.validation.Valid;
import com.example.edurankai.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> createStudent(
            @Valid @RequestBody StudentRequest request) {

        Student savedStudent = studentService.createStudent(request);

        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String course,
            @RequestParam(required = false) Student.Status status) {

        return ResponseEntity.ok(
                studentService.searchStudents(search, course, status)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequest request) {

        return ResponseEntity.ok(
                studentService.updateStudent(id, request)
        );



    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {

        studentService.deleteStudent(id);

        return ResponseEntity.noContent().build();
    }
    @GetMapping("/dashboard")
    public ResponseEntity<DashboardResponse> getDashboardStats() {

        return ResponseEntity.ok(studentService.getDashboardStats());
    }


}
