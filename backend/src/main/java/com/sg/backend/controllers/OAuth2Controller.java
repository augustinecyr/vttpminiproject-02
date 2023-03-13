package com.sg.backend.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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

    return "redirect:http://localhost:4200/token";

  }

  @GetMapping("/login/oauth2/code/github")
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  public String handleGithubCallback(@AuthenticationPrincipal OAuth2User principal) {

    return "redirect:http://localhost:4200/login/oauth/access_token";
  }

  @PostMapping("/login/oauth/access_token")
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  public Map<String, String> exchangeAuthorizationCodeForToken(@RequestParam("code") String code) {
    // Send a POST request to https://github.com/login/oauth/access_token
    // with the authorization code to exchange it for an access token
    System.out.println(code);
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Type", "application/x-www-form-urlencoded");
    headers.set("Accept", "application/json");

    String accessTokenEndpoint = UriComponentsBuilder
        .fromUriString("https://github.com/login/oauth/access_token")
        .queryParam("client_id", GITHUB_ID)
        .queryParam("client_secret", GITHUB_SECRET)
        .queryParam("code", code)
        .toUriString();

    System.out.println(accessTokenEndpoint);

    HttpEntity<String> requestEntity = new HttpEntity<>(headers);

    ResponseEntity<Map<String, String>> responseEntity = restTemplate.exchange(accessTokenEndpoint, HttpMethod.POST,
        requestEntity, new ParameterizedTypeReference<Map<String, String>>() {});

    System.out.println(responseEntity);

    if (responseEntity.getStatusCode() == HttpStatus.OK) {
      // Parse the access token from the response body
      Map<String, String> responseBody = responseEntity.getBody();
      String accessToken = responseBody.get("access_token");
      System.out.println(accessToken);
      return responseBody;
    } else {
      throw new RuntimeException("Failed to exchange authorization code for access token");

    }

  }

  @PostMapping("/token")
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  public Map<String, String> exchangeGoogleCodeForToken(@RequestParam("code") String code) {
    // Send a POST request to https://oauth2.googleapis.com/token 
    // with the authorization code to exchange it for an access token
    System.out.println(code);
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Type", "application/x-www-form-urlencoded");
    headers.set("Accept", "application/json");

    String accessTokenEndpoint = UriComponentsBuilder
        .fromUriString("https://oauth2.googleapis.com/token")
        .queryParam("client_id", GOOGLE_ID)
        .queryParam("client_secret", GOOGLE_SECRET)
        .queryParam("code", code)
        .queryParam("grant_type", "authorization_code")
        .queryParam("redirect_uri", "http://localhost:4200/token")
        .toUriString();

    System.out.println(accessTokenEndpoint);

    HttpEntity<String> requestEntity = new HttpEntity<>(headers);

    ResponseEntity<Map<String, String>> responseEntity = restTemplate.exchange(accessTokenEndpoint, HttpMethod.POST,
        requestEntity, new ParameterizedTypeReference<Map<String, String>>() {});

    System.out.println(responseEntity);

    if (responseEntity.getStatusCode() == HttpStatus.OK) {
      // Parse the access token from the response body
      Map<String, String> responseBody = responseEntity.getBody();
      String accessToken = responseBody.get("access_token");
      System.out.println(accessToken);
      return responseBody;
    } else {
      throw new RuntimeException("Failed to exchange authorization code for access token");

    }

  }

}
