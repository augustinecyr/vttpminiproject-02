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

import com.sg.backend.models.Club;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonString;
import jakarta.json.JsonValue;

@Service
public class ClubService {
    
    private static final String URL = "https://transfermarket.p.rapidapi.com/clubs/get-squad";

	@Value("${X_RapidAPI_Key}")
	private String rapidAPI;

    public List<Club> getSquad(String id) {

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
					.header("X-RapidAPI-Key", "%s".formatted(rapidAPI))// rapidAPI key
					.build();

			System.out.println(">>> [url]: " + url); // prints out the URL that is built

			System.out.println("-----------------------------------------------------------");

			RestTemplate template = new RestTemplate();

			ResponseEntity<String> resp;
			resp = template.exchange(req, String.class);

			payload = resp.getBody();

			System.out.println(">>> [payload]: " + payload); // the payload
			System.out.println("-----------------------------------------------------------");

		} catch (Exception ex) {
			System.err.printf("Error: %s\n", ex.getMessage()); // troubleshooting
			return Collections.emptyList();
		}

		List<Club> squads = new LinkedList<>();

		try (StringReader strReader = new StringReader(payload)) {
			JsonReader r = Json.createReader(strReader);
			JsonObject j = r.readObject();

			JsonArray squadList = j.getJsonArray("squad");
			JsonObject first = squadList.getJsonObject(0); 
			JsonString firstName = first.getJsonString("name");
			
			System.out.println("Total number of players: " + squadList.size());
			System.out.println("-----------------------------------------------------------");
			System.out.println("Top of the table: " + firstName);
			System.out.println("-----------------------------------------------------------");
	

			for (JsonValue v : j.getJsonArray("squad")) {

				squads.add(Club.create((JsonObject) v));

			}

		}

		return squads;
	}
}
