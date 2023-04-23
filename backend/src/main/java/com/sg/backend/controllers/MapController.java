package com.sg.backend.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MapController {

    @Value("${GOOGLE_MAPS_API_KEY}")
    private String googleMapsKey;

    @GetMapping("/googlemap")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public String getMapsEmbedUrl() {
        // construct the url for the Google Maps Embed API
        // hide API key from frontend
        String placeId = "ChIJaX_k53Kue0gR_lVE35YOk2w";
        return "https://www.google.com/maps/embed/v1/place?q=place_id:" + placeId + "&key=" + googleMapsKey;
    }

}
