package com.sg.backend.service;

import java.io.StringReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.sg.backend.models.Stats;
import com.sg.backend.repositories.StatsRepository;

import jakarta.json.Json;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonString;
import jakarta.json.JsonValue;

@Service
public class StatsService {

        @Autowired
        private StatsRepository statsRepo;

        private static final String URL = "https://transfermarket.p.rapidapi.com/players/get-performance-summary";

        @Value("${X_RapidAPI_Key}")
        private String rapidAPI;

        public List<Stats> getStats(String id) {

                String payload;
                System.out.println("Attempting request to TransferMarkt.com....");

                try {

                        String url = UriComponentsBuilder
                                        .fromUriString(URL)
                                        .queryParam("id", id)
                                        .queryParam("domain", "com")
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
                try (StringReader strReader = new StringReader(payload)) {
                        JsonReader r = Json.createReader(strReader);
                        JsonObject j = r.readObject();

                        List<Stats> stats = new LinkedList<>();

                        JsonString goalsScored = j.getJsonArray("competitionPerformanceSummery")
                                        .getJsonObject(0) // premier league is the first competition
                                        .getJsonObject("performance")
                                        .getJsonString("goals");
                        int goals = Integer.parseInt(goalsScored.getString());

                        JsonString yellowCardsJson = j.getJsonArray("competitionPerformanceSummery")
                                        .getJsonObject(0)
                                        .getJsonObject("performance")
                                        .getJsonString("yellowCards");
                        int yellowCards = Integer.parseInt(yellowCardsJson.getString());

                        JsonString redCardsJson = j.getJsonArray("competitionPerformanceSummery")
                                        .getJsonObject(0)
                                        .getJsonObject("performance")
                                        .getJsonString("redCards");
                        int redCards = Integer.parseInt(redCardsJson.getString());

                        JsonString assistsJson = j.getJsonArray("competitionPerformanceSummery")
                                        .getJsonObject(0)
                                        .getJsonObject("performance")
                                        .getJsonString("assists");
                        int assists = Integer.parseInt(assistsJson.getString());

                        JsonNumber cleanSheetsJson = j.getJsonArray("competitionPerformanceSummery")
                                        .getJsonObject(0)
                                        .getJsonObject("performance")
                                        .getJsonNumber("toNil");
                        int cleanSheets = cleanSheetsJson.intValue();
                        // cast error resolved
                        JsonValue isGoalkeeperValue = j.getJsonArray("competitionPerformanceSummery")
                                        .getJsonObject(0)
                                        .getJsonObject("performance")
                                        .get("isGoalkeeper");
                        boolean isGoalkeeper = Boolean.parseBoolean(isGoalkeeperValue.toString());

                        // put all the values into the object and then put the object into the stats
                        // list
                        Stats s = new Stats();
                        int clubPlayerId = Integer.parseInt(id);
                        s.setClubPlayerId(clubPlayerId);
                        s.setGoals(goals);
                        s.setYellowCards(yellowCards);
                        s.setRedCards(redCards);
                        s.setAssists(assists);
                        s.setCleanSheets(cleanSheets);
                        s.setGoalkeeper(isGoalkeeper);
                        stats.add(s);
                        List<String> clubPlayerIds = statsRepo.getClubPlayerIds();
                        if (clubPlayerIds.contains(id)) {
                                System.out.println("Player already has existing statistics in database.");
                                System.out.println("-------------------------");
                                return stats;
                        }
                        statsRepo.insert(s);
                        System.out.println(stats);
                        return stats;
                }

        }

}
