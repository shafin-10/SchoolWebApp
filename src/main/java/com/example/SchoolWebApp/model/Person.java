package com.example.SchoolWebApp.model;

import com.example.SchoolWebApp.annotations.FieldValueMatch;
import com.example.SchoolWebApp.annotations.PasswordValidator;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

import java.security.PrivateKey;

@Getter
@Setter
@Entity
@FieldValueMatch.List({
        @FieldValueMatch(
                field = "pwd",
                fieldMatch = "confirmPwd",
                message = "Passwords do not match"
        ),
        @FieldValueMatch(
                field = "email",
                fieldMatch = "confirmEmail",
                message = "Emails do not match"
        )
})
public class Person extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int personId;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, message = "Name must be atleast 3 characters long")
    private String name;

    @NotBlank(message = "Mobile number cannot be blank")
    @Pattern(regexp = "(^$|[0-9]{11})", message = "Mobile number must be atleast 11 characters long")
    private String mobileNumber;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Please enter a valid email address")
    private String email;

    @NotBlank(message = "Confirm email cannot be blank")
    @Email(message = "Please provide a valid confirm email address")
    @Transient
    private String confirmEmail;

    @NotBlank(message = "Password must not be blank")
    @Size(min = 5, message = "Password must be atleast 5 characters long")
    @PasswordValidator
    private String pwd;

    @NotBlank(message = "Confirm password must not be blank")
    @Size(min = 5, message = "Password passwor must be at least 5 characters long")
    @Transient
    private String confirmPwd;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, targetEntity = Roles.class)
    @JoinColumn(name = "role_id", referencedColumnName = "roleId", nullable = false)
    private Roles roles;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId", nullable = true)
    private Address address;
}
