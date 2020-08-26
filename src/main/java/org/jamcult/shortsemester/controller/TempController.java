package org.jamcult.shortsemester.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("msg", "Mantap");
        return "index";
    }
}
