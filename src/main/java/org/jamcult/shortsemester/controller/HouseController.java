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

import java.util.Optional;

@Controller //TODO Integerate with View & Validation
@RequestMapping("/house")
public class HouseController {
    @Autowired
    private HouseRepository repository;

    @GetMapping(path = {"/", ""})
    @ResponseBody
    public String index() {
        Iterable<House> houses = repository.findAll();
        StringBuilder builder = new StringBuilder();
        houses.forEach(house -> builder.append(house).append("\n"));
        return builder.toString();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public String get(@PathVariable int id) {
        Optional<House> house = repository.findById(id);
        if (house.isPresent())
            return house.get().toString();
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping(path = {"/", ""})
    @ResponseStatus(HttpStatus.CREATED)
    public void create(House house) {
        repository.save(house);
        System.out.println(house);
    }

    @GetMapping("/create")
    public String createPage(Model model) {
        model.addAttribute("msg", "Create");
        return "index";
    }

    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable int id, Model model) {
        Optional<House> house = repository.findById(id);
        if (house.isPresent()) {
            model.addAttribute("msg", id + " Edit");
            return "index";
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{id}")
    public void update(@PathVariable int id, House updatedHouse) {
        Optional<House> house = repository.findById(id);
        if (house.isPresent() && id == updatedHouse.getId()) {
            System.out.println(updatedHouse);
            repository.save(updatedHouse);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}/delete")
    public void delete(@PathVariable int id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
