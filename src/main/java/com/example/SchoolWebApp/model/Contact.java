package com.example.SchoolWebApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "contact_msg")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private int contact_id;

    @Column(name = "name")
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, message = "Name should be greater than 3")
    private String name;

    @Column(name = "mobileNum")
    @NotBlank(message = "Mobile Number cannot be blank")
    @Pattern(regexp = "(^$|[0-9]{11})", message = "Mobile number must be 11 digits")
    private String mobileNum;

    @Column(name = "email")
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Please provide a valid email address")
    private String email;

    @Column(name = "subject")
    @NotBlank(message = "Subject must not be blank")
    @Size(min = 5, message = "Subject must be at least 5 characters long")
    private String subject;

    @Column(name = "message")
    @NotBlank(message = "Message must not be blank")
    @Size(min = 10, message = "Message must be at least 10 characters long")
    private String message;

    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;
}
