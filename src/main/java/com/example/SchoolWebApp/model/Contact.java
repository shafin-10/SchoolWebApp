package com.example.SchoolWebApp.model;

import lombok.Data;

@Data
public class Contact {
    private String name;
    private String mobileNum;
    private String email;
    private String subject;
    private String message;

    private String status;

}
