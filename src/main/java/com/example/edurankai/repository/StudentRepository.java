package com.example.edurankai.repository;

import com.example.edurankai.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByEmail(String email);
    List<Student> findByDeletedFalse();

    Optional<Student> findByIdAndDeletedFalse(Long id);
    long countByDeletedFalse();

    long countByDeletedFalseAndStatus(Student.Status status);

}



