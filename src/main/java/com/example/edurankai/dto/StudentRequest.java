
package com.example.edurankai.dto;

import com.example.edurankai.entity.Student;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StudentRequest {

    @NotBlank
    private String fullName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "^[0-9]{10}$",
            message = "Phone number must contain exactly 10 digits")
    private String phoneNumber;

    @NotBlank
    private String course;

    @NotNull
    private LocalDate enrollmentDate;

    @NotNull
    private Student.Status status;
}
