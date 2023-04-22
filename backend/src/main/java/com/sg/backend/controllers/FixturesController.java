package com.sg.backend.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sg.backend.models.Fixtures;
import com.sg.backend.service.FixturesService;

@RestController
@RequestMapping
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FixturesController {

    @Autowired
    private FixturesService fixturesSvc;

    @GetMapping(path = "/fixtures/matchweek")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<Fixtures> getFixtures(HttpSession sess, @RequestParam String dayID){
        fixturesSvc.getFixtures(dayID);
        List<Fixtures> fixtures = fixturesSvc.getFixtures(dayID);
        return fixtures;
    }
    
}
