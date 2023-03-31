package com.sg.backend.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sg.backend.models.Club;
import com.sg.backend.service.ClubService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClubController {

    @Autowired
    private ClubService clubSvc;
  
    @GetMapping(path = "/club/squad")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<Club> getSquad (HttpSession sess, @RequestParam String id){
        clubSvc.getSquad(id);
       List<Club> squads = clubSvc.getSquad(id); 
        return squads;
    }
    
}
