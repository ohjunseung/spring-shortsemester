package org.jamcult.shortsemester.controller;

import org.jamcult.shortsemester.model.Admin;
import org.jamcult.shortsemester.model.House;
import org.jamcult.shortsemester.repository.HouseRepository;
import org.jamcult.shortsemester.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller //TODO Admin page
public class MainController {
    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private AdminService adminService;

    @GetMapping("/")
    public String index(Model model) {
        Iterable<House> houses = houseRepository.findAll();
        model.addAttribute("houses", houses);
        return "index";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("admin", new Admin());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Admin admin, RedirectAttributes redirectAttributes) {
        try {
            if (admin.getPassword().chars().allMatch(Character::isWhitespace)
                    || admin.getUsername().chars().allMatch(Character::isWhitespace)) {
                throw new JpaSystemException(new RuntimeException());
            }

            adminService.signUp(admin);
            redirectAttributes.addAttribute("msg", "New Admin created!");
            return "redirect:/house";
        } catch (JpaSystemException e) {
            return "redirect:/register?error";
        }
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/contacts")
    public String contacts() {
        return "contacts";
    }
}
