package com.sg.backend.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OAuth2Controller {

  @Value("${GITHUB_ID}")
  private String GITHUB_ID;

  @Value("${GITHUB_SECRET}")
  private String GITHUB_SECRET;

  @Value("${GOOGLE_ID}")
  private String GOOGLE_ID;

  @Value("${GOOGLE_SECRET}")
  private String GOOGLE_SECRET;

  @GetMapping("/auth/google/callback")
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  public String handleGoogleCallback(@AuthenticationPrincipal OAuth2User principal) {

    return "redirect:http://localhost:4200";

  }

  @GetMapping("/login/oauth2/code/github")
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  public String handleGithubCallback(@AuthenticationPrincipal OAuth2User principal) {

    return "redirect:http://localhost:4200";
  }

}
