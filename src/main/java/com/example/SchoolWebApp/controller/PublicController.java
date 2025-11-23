package com.example.SchoolWebApp.controller;

import com.example.SchoolWebApp.model.Person;
import com.example.SchoolWebApp.service.PersonSerive;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/public")
public class PublicController {

    @Autowired
    PersonSerive personSerive;

    @RequestMapping(value = "/register", method = {RequestMethod.GET})
    public String displayRegisterPage(Model model){
        model.addAttribute("person", new Person());
        return "register.html";
    }

    @RequestMapping(value = "/createUser", method = {RequestMethod.POST})
    public String createNewUser(@Valid @ModelAttribute("person") Person person, Errors errors){
        if(errors.hasErrors()){
            return "register.html";
        }
        boolean isSaved = personSerive.createNewUser(person);
        if(isSaved){
            return "redirect:/login?register=true";
        }
        else{
            return "register.html";
        }
    }

}
