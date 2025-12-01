package com.example.SchoolWebApp.repository;

import com.example.SchoolWebApp.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

   /* @Query(value = "SELECT * FROM contact_msg c WHERE c.status = 'Open'",
            nativeQuery = true)
   List<Contact> findByStatus();*/

    Page<Contact> findByStatus(String status, Pageable pageable);
}
