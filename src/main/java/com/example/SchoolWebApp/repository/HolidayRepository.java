package com.example.SchoolWebApp.repository;

import com.example.SchoolWebApp.model.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, String> {

}
