package com.sg.backend.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sg.backend.models.Stats;
import com.sg.backend.service.StatsService;

@RestController
@RequestMapping
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StatsController {
    
    @Autowired
    private StatsService statsSvc;

    @GetMapping(path="/players/stats")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<Stats> getStats(HttpSession sess, @RequestParam String id){
        statsSvc.getStats(id);
        List<Stats> stats = statsSvc.getStats(id);
        return stats;
    }
}
