
package com.example.edurankai.service;
import com.example.edurankai.dto.StudentRequest;

import com.example.edurankai.entity.Student;
import com.example.edurankai.repository.StudentRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.example.edurankai.dto.DashboardResponse;
import com.example.edurankai.exception.StudentNotFoundException;
import com.example.edurankai.exception.DuplicateEmailException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Student createStudent(StudentRequest request) {

        if (studentRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException("Email already exists");
        }

        Student student = new Student();

        student.setFullName(request.getFullName());
        student.setEmail(request.getEmail());
        student.setPhoneNumber(request.getPhoneNumber());
        student.setCourse(request.getCourse());
        student.setEnrollmentDate(request.getEnrollmentDate());
        student.setStatus(request.getStatus());

        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findByDeletedFalse();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student not found with ID: " + id
                ));
    }

    public Student updateStudent(Long id, StudentRequest request) {

        Student student = getStudentById(id);

        if (!student.getEmail().equals(request.getEmail())
                && studentRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException("Email already exists");
        }

        student.setFullName(request.getFullName());
        student.setEmail(request.getEmail());
        student.setPhoneNumber(request.getPhoneNumber());
        student.setCourse(request.getCourse());
        student.setEnrollmentDate(request.getEnrollmentDate());
        student.setStatus(request.getStatus());

        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        student.setDeleted(true);
        studentRepository.save(student);
    }

    public List<Student> searchStudents(
            String search,
            String course,
            Student.Status status) {
        System.out.println("Search value: " + search);

        List<Student> result = studentRepository.findByDeletedFalse()
                .stream()
                .filter(student ->
                        search == null || search.isBlank()
                                || student.getFullName().toLowerCase()
                                .contains(search.toLowerCase())
                                || student.getEmail().toLowerCase()
                                .contains(search.toLowerCase()))
                .filter(student ->
                        course == null || course.isBlank()
                                || student.getCourse().equalsIgnoreCase(course))
                .filter(student ->
                        status == null || student.getStatus() == status)
                .toList();


        return result;
    }
    public DashboardResponse getDashboardStats() {

        long total = studentRepository.countByDeletedFalse();

        long active = studentRepository.countByDeletedFalseAndStatus(
                Student.Status.ACTIVE
        );

        long inactive = studentRepository.countByDeletedFalseAndStatus(
                Student.Status.INACTIVE
        );

        return new DashboardResponse(total, active, inactive);
    }






}
