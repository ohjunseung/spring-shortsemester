package org.jamcult.shortsemester.controller;

import org.jamcult.shortsemester.model.House;
import org.jamcult.shortsemester.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    private HouseRepository houseRepository;

    @GetMapping("/")
    public String index(Model model) {
        Iterable<House> houses = houseRepository.findAll();
        model.addAttribute("houses", houses);
        return "index";
    }
    //TODO Spring security
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/contacts")
    public String contacts(){
        return "contacts";
    }
}
