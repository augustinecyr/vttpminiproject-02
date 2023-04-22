package com.sg.backend.service;

import java.io.StringReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.sg.backend.models.Fixtures;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

@Service
public class FixturesService {

    private static final String URL = "https://transfermarket.p.rapidapi.com/matches/list-by-game-plan";

    @Value("${X_RapidAPI_Key}")
    private String rapidAPI;

    public List<Fixtures> getFixtures(String dayID) {
        String payload;
        System.out.println("Attempting request to TransferMarkt.com....");

        try {

            String url = UriComponentsBuilder
                    .fromUriString(URL)
                    .queryParam("seasonID", "2022")
                    .queryParam("leagueID", "GB1")
                    .queryParam("domain", "com")
                    .queryParam("dayID", dayID)
                    .encode()
                    .toUriString();

            RequestEntity<Void> req = RequestEntity
                    .get(url)
                    .header("X-RapidAPI-Key", "%s".formatted(rapidAPI))
                    .build();

            System.out.println(">>> [url]: " + url);

            System.out.println("-----------------------------------------------------------");

            RestTemplate template = new RestTemplate();

            ResponseEntity<String> resp;
            resp = template.exchange(req, String.class);

            payload = resp.getBody();

            System.out.println(">>> [payload]: " + payload);
            System.out.println("-----------------------------------------------------------");

        } catch (Exception ex) {
            System.err.printf("Error: %s\n", ex.getMessage());
            return Collections.emptyList();
        }

        List<Fixtures> fixtures = new LinkedList<>();

        try (StringReader strReader = new StringReader(payload)) {
            JsonReader r = Json.createReader(strReader);
            JsonObject j = r.readObject();

            JsonArray fixturesArr = j.getJsonArray("playDayMatches");

            System.out.println("Total number of fixtures: " + fixturesArr.size());
            System.out.println("-----------------------------------------------------------");

            for (JsonValue v : j.getJsonArray("playDayMatches")) {
                if (v.getValueType() == JsonValue.ValueType.OBJECT) {
                    try {
                        fixtures.add(Fixtures.create((JsonObject) v));
                    } catch (Exception ex) {
                        System.err.printf("Error: %s\n", ex.getMessage());
                    }
                }
            }
            for (Fixtures fixture : fixtures) {
                System.out.println(fixture.getFullMatchDate() + ": " + fixture.getMatchTime() + ": "
                        + fixture.getHomeClubName() + ": " + fixture.getHomeClubImage() + ": "
                        + fixture.getAwayClubName() + ": " + fixture.getAwayClubImage() + ": " + fixture.getResult());
            }

        }
        return fixtures;

    }
}
