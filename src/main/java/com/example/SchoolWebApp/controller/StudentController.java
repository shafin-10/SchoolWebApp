package com.example.SchoolWebApp.controller;

import com.example.SchoolWebApp.model.Courses;
import com.example.SchoolWebApp.model.Person;
import com.example.SchoolWebApp.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private PersonRepository personRepository;

    @RequestMapping("/displayCourses")
    public ModelAndView displayCourses(Model model, HttpSession session){
        Person person = (Person) session.getAttribute("loggedInUser");
        Person personEntity = personRepository.readByEmail(person.getEmail());
        List<Courses> courses = personEntity.getCourses().stream().toList();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("courses_enrolled.html");
        model.addAttribute("person", person);
        model.addAttribute("courses", courses);
        return modelAndView;
    }
}
