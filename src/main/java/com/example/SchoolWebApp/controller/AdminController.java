package com.example.SchoolWebApp.controller;

import com.example.SchoolWebApp.model.Courses;
import com.example.SchoolWebApp.model.Person;
import com.example.SchoolWebApp.model.SchoolClass;
import com.example.SchoolWebApp.repository.CoursesRepository;
import com.example.SchoolWebApp.repository.PersonRepository;
import com.example.SchoolWebApp.repository.SchoolClassRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private SchoolClassRepository schoolClassRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CoursesRepository coursesRepository;;

    @RequestMapping("/displayClasses")
    public ModelAndView displayClasses(Model model){
        ModelAndView modelAndView = new ModelAndView("class.html");
        List<SchoolClass> schoolClasses = schoolClassRepository.findAll();
        model.addAttribute("eazyClasses", schoolClasses);
        model.addAttribute("eazyClass", new SchoolClass());
        return modelAndView;
    }

    @RequestMapping(value = "/addNewClass", method = RequestMethod.POST)
    public String addNewClass(@ModelAttribute("SchoolClass") SchoolClass schoolClass, Model model){
        schoolClassRepository.save(schoolClass);
        return "redirect:/admin/displayClasses";
    }

    @RequestMapping("/deleteClass")
    public String deleteClass(@RequestParam int id, Model model){
        Optional<SchoolClass> schoolClassList = schoolClassRepository.findById(id);
        for(Person it : schoolClassList.get().getPersons()){
            it.setSchoolClass(null);
            personRepository.save(it);
        }
        schoolClassRepository.deleteById(id);
        return "redirect:/admin/displayClasses";
    }

    @RequestMapping("/displayStudents")
    public ModelAndView displayStudents(@RequestParam int classId, Model model, HttpSession session,
    @RequestParam(value = "error", required = false)String error){
        String errorMsg = null;
        ModelAndView modelAndView = new ModelAndView("students.html");
       Optional<SchoolClass> schoolClass = schoolClassRepository.findById(classId);
       modelAndView.addObject("eazyClass",schoolClass.get());
       modelAndView.addObject("person",new Person());
        session.setAttribute("currentClass", schoolClass.get());
        if(error != null){
            errorMsg = "Invalid email entered!";
            model.addAttribute("errorMessage", errorMsg);
        }
        return modelAndView;
    }

    @RequestMapping("/addStudent")
    public ModelAndView addStudentInClass(@ModelAttribute("person") Person person, HttpSession session, Model model, Errors errors){
        ModelAndView modelAndView = new ModelAndView();
        SchoolClass schoolClass = (SchoolClass) session.getAttribute("currentClass");
        Person personEntity = personRepository.readByEmail(person.getEmail());
        if(personEntity == null || !(personEntity.getPersonId() > 0)){
            modelAndView.setViewName("redirect:/admin/displayStudents?classId=" + schoolClass.getClassId() +
                    "&error=true");
           return modelAndView;
        }
        personEntity.setSchoolClass(schoolClass);
        personRepository.save(personEntity);
        schoolClass.getPersons().add(personEntity);
        schoolClassRepository.save(schoolClass);
        modelAndView.setViewName("redirect:/admin/displayStudents?classId=" + schoolClass.getClassId());
        return modelAndView;
    }

    @RequestMapping("/deleteStudent")
    public String deleteStudent(@RequestParam int personId, HttpSession session){
        Optional<Person> person = personRepository.findById(personId);
        SchoolClass schoolClass = (SchoolClass) session.getAttribute("currentClass");
        person.get().setSchoolClass(null);
        schoolClass.getPersons().remove(person.get());
        personRepository.save(person.get());
        SchoolClass schoolClassUpdated = schoolClassRepository.save(schoolClass);
        session.setAttribute("currentClass",schoolClassUpdated );
        return "redirect:/admin/displayStudents?classId=" + schoolClass.getClassId();
    }

    @RequestMapping("/displayCourses")
    public ModelAndView displayCourses(Model model){
        ModelAndView modelAndView = new ModelAndView("courses_secure.html");
        List<Courses> courses = coursesRepository.findAll();
        modelAndView.addObject("course", new Courses());
        modelAndView.addObject("courses",courses);
        return modelAndView;
    }

    @PostMapping("/addNewCourse")
    public ModelAndView addNewCourse(Model model,@ModelAttribute("courses") Courses courses){
        ModelAndView modelAndView = new ModelAndView();
        coursesRepository.save(courses);
        modelAndView.setViewName("redirect:/admin/displayCourses");
        return modelAndView;
    }

    @RequestMapping("/viewStudents")
    public ModelAndView viewStudents(Model model, @RequestParam int id, HttpSession session,
                                     @RequestParam(value = "error", required = false) String error){
        String errorMsg = null;
        ModelAndView modelAndView = new ModelAndView("course_students.html");
        Optional<Courses> courses = coursesRepository.findById(id);

        modelAndView.addObject("courses", courses.get());
        modelAndView.addObject("person", new Person());
        session.setAttribute("CurrentCourse",courses.get());
        if(error != null){
            errorMsg = "Invalid email entered!";
            model.addAttribute("errorMessage", errorMsg);
        }
        return modelAndView;
    }

    @RequestMapping("/addStudentToCourse")
    public String addStudentToCourse(HttpSession session,@ModelAttribute("person")Person person, Model model){
        Courses courses = (Courses) session.getAttribute("CurrentCourse");
        Person personEntity = personRepository.readByEmail(person.getEmail());
        if(personEntity == null || !(personEntity.getPersonId() > 0)){
            return "redirect:/admin/viewStudents?id="+courses.getCourseId()+"&error=true";
        }
        personEntity.getCourses().add(courses);
        personRepository.save(personEntity);
        courses.getPersons().add(personEntity);
       coursesRepository.save(courses);
        session.setAttribute("CurrentCourse", courses);
        return "redirect:/admin/viewStudents?id=" + courses.getCourseId();
    }

    @RequestMapping("/deleteStudentFromCourse")
    public String deleteStudentFromCourse(HttpSession session, @RequestParam int personId){
        Courses courses = (Courses) session.getAttribute("CurrentCourse");
        Optional<Person> personEntity = personRepository.findById(personId);
        personEntity.get().getCourses().remove(courses);
        personRepository.save(personEntity.get());
        courses.getPersons().remove(personEntity.get());
        coursesRepository.save(courses);
        session.setAttribute("CurrentCourse", courses);
        return "redirect:/admin/viewStudents?id=" + courses.getCourseId();
    }
}
