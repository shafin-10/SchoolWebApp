package com.example.SchoolWebApp.service;

import com.example.SchoolWebApp.constants.SchoolConstants;
import com.example.SchoolWebApp.model.Contact;
import com.example.SchoolWebApp.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public boolean saveMsgDetails(Contact contact){
        boolean isSaved = false;
        contact.setStatus(SchoolConstants.OPEN);
        Contact savedContact = contactRepository.save(contact);
        if(savedContact != null && savedContact.getContact_id() > 0){
            isSaved = true;
        }
        if(isSaved)
             log.info("contact msg saved");
        return isSaved;
    }

    public List<Contact> getMsgsWithOpenStatus() {
        return contactRepository.findByStatus();
    }

    public boolean updateMsgStatus(int id) {
        Optional<Contact> contact = contactRepository.findById(id);

        if (contact.isPresent()) {
            contact.get().setStatus("Close");
            Contact saved = contactRepository.save(contact.get());
            return saved != null;
        } else {
            return false;
        }
    }

}
