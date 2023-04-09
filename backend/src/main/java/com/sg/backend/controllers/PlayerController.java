package com.sg.backend.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sg.backend.models.PlayerSQL;
import com.sg.backend.repositories.ClubRepository;


@RestController
@RequestMapping
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PlayerController {
   
    @Autowired
    private ClubRepository clubRepo;
    
    // use this to retrieve from SQL
    @GetMapping(path = "/players")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<PlayerSQL> getPlayerList(HttpSession sess){
       List<PlayerSQL> players = clubRepo.getPlayerList();
        return players;
    }
}
