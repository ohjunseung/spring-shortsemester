package org.jamcult.shortsemester.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("msg", "Mantap");
        return "index";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }
}
