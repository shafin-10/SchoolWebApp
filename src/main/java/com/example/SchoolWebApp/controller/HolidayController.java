package com.example.SchoolWebApp.controller;

import com.example.SchoolWebApp.model.Holiday;
import com.example.SchoolWebApp.repository.HolidayRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Controller
public class HolidayController {

    @Autowired
    private HolidayRepository holidayRepository;

    @GetMapping("/holidays/{display}")
    public String displayHolidays(@PathVariable String display,
                                  Model model) {
        if (null != display && display.equals("all")) {
            model.addAttribute("festival", true);
            model.addAttribute("federal", true);
        } else if (null != display && display.equals("federal")) {
            model.addAttribute("federal", true);
        } else if (null != display && display.equals("festival")) {
            model.addAttribute("festival", true);
        }

        // jdbc -> List<Holiday> holidays = holidayRepository.findAllHolidays();

        /*Iterable<Holiday> holidays = holidayRepository.findAll();
        List<Holiday> holidayList = StreamSupport.stream(holidays.spliterator(), false)
                .collect(Collectors.toList());
        Holiday.Type[] types = Holiday.Type.values();
        for (Holiday.Type type : types) {
            model.addAttribute(type.toString(),
                    (holidayList.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList())));
        }

        return "holidays";*/

        List<Holiday> holidays = holidayRepository.findAll();
        List<Holiday> festival_holiday = new ArrayList<>();
        List<Holiday> federal_holiday = new ArrayList<>();
        for(Holiday holiday : holidays){
            if(holiday.getType() == Holiday.Type.FESTIVAL){
                festival_holiday.add(holiday);
            }
            else if(holiday.getType() == Holiday.Type.FEDERAL){
                federal_holiday.add(holiday);
            }
        }

        model.addAttribute("FESTIVAL", festival_holiday);
        model.addAttribute("FEDERAL", federal_holiday);

        return "holidays";
    }

}
