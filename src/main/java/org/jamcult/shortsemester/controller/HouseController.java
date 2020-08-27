package org.jamcult.shortsemester.controller;

import org.jamcult.shortsemester.model.House;
import org.jamcult.shortsemester.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Controller
@RequestMapping("/house")
public class HouseController {
    @Autowired
    private HouseRepository repository;

    @GetMapping(path = {"/", ""})
    public String index() {
        return "index";
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
    public void create() {
        House house = new House("test", "test", 2000);
        repository.save(house);
    }

    @GetMapping("/create")
    public void createPage() {

    }

    @GetMapping("/{id}/edit")
    public void editPage(@PathVariable int id) {

    }

    @PostMapping("/{id}")
    public void update(@PathVariable int id) {

    }

    @PostMapping("/{id}/delete")
    public void delete(@PathVariable int id) {

    }
}
