package org.jamcult.shortsemester.controller;

import org.jamcult.shortsemester.model.House;
import org.jamcult.shortsemester.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller //TODO Validation, Flash attributes
@RequestMapping("/house")
public class HouseController {
    @Autowired
    private HouseRepository repository;

    @GetMapping(path = {"/", ""})
    public String get(Model model) {
        Iterable<House> houses = repository.findAll();
        model.addAttribute("houses", houses);
        return "house-index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model) {
        Optional<House> house = repository.findById(id);
        if (house.isPresent()) {
            model.addAttribute(house.get());
            return "house-detail";
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping(path = {"/", ""})
    public String create(@ModelAttribute House house, RedirectAttributes redirectAttributes) {
        repository.save(house);
        return "redirect:/house";
    }

    @GetMapping("/create")
    public String createPage(Model model) {
        model.addAttribute(new House());
        return "house-create";
    }

    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable int id, Model model) {
        Optional<House> house = repository.findById(id);
        if (house.isPresent()) {
            model.addAttribute(house.get());
            return "house-edit";
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{id}")
    public String update(@PathVariable int id, House updatedHouse, RedirectAttributes redirectAttributes) {
        Optional<House> house = repository.findById(id);
        if (house.isPresent()) {
            repository.save(updatedHouse);
            return "redirect:/house";
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            repository.deleteById(id);
            return "redirect:/house";
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
