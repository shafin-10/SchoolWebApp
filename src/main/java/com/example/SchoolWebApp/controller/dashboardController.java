package com.example.SchoolWebApp.controller;

import com.example.SchoolWebApp.model.Person;
import com.example.SchoolWebApp.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class dashboardController {

    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/dashboard")
    public String displayDashboard(Authentication auth, Model model, HttpSession session){
        Person person = personRepository.readByEmail(auth.getName());
        model.addAttribute("username", person.getName());
        model.addAttribute("roles", auth.getAuthorities());
        session.setAttribute("loggedInUser",person);
        return "dashboard.html";
    }
}
