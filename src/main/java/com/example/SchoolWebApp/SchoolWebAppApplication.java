package com.example.SchoolWebApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "AuditAwareImpl")
public class SchoolWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolWebAppApplication.class, args);
		System.out.println("-------------" +
				"Application started" +
				"------------");
	}

}
