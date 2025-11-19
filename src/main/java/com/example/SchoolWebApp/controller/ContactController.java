package com.example.SchoolWebApp.controller;

import com.example.SchoolWebApp.model.Contact;
import com.example.SchoolWebApp.service.ContactService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Slf4j
@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @RequestMapping("/contact")
    public String contactForm(Model model){
        model.addAttribute("contact", new Contact());
        return "contact.html";
    }

   @PostMapping("/saveMsg")
    public String saveMsg(@Valid @ModelAttribute("contact") Contact contact, Errors errors){
        contact.setCreatedBy("Anonymous");
        contact.setCreatedAt(LocalDateTime.now());
        if(errors.hasErrors()){
            log.info("Contact from validation failed due to " + errors.toString());
            return "contact";
        }
        contactService.saveMsgDetails(contact);
        return "redirect:/contact";
    }

    @GetMapping("/displayMessages")
    public ModelAndView displayMessage(Model model){
        List<Contact> contactList = contactService.getMsgsWithOpenStatus();
        ModelAndView modelAndView = new ModelAndView("messages.html");
        modelAndView.addObject("contactMsgs",contactList);
        return modelAndView;
    }

    @RequestMapping("/closeMsg")
    public String updateMsgStatus(@RequestParam int id){
        contactService.updateMsgStatus(id);
        return "redirect:/displayMessages";
    }

}
