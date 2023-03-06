package com.sg.backend.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@CrossOrigin(origins = "*")
public class GoogleController {
    @GetMapping("/auth/google/callback")
    public String handleGoogle(@AuthenticationPrincipal OAuth2User principal) {
      // Do something with the user data
      return "redirect:http://localhost:4200/";
    }
}
