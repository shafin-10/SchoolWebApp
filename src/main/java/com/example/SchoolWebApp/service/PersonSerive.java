package com.example.SchoolWebApp.service;

import com.example.SchoolWebApp.constants.SchoolConstants;
import com.example.SchoolWebApp.model.Person;
import com.example.SchoolWebApp.model.Roles;
import com.example.SchoolWebApp.repository.PersonRepository;
import com.example.SchoolWebApp.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonSerive {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean createNewUser(Person person){
        boolean isSaved = false;
        Roles roles = rolesRepository.getByRoleName(SchoolConstants.STUDENT_ROLE);
        person.setRoles(roles);
        person.setPwd(passwordEncoder.encode(person.getPwd()));
        person = personRepository.save(person);
        if(person.getPersonId() > 0){
            isSaved = true;
        }
        return isSaved;
    }
}
