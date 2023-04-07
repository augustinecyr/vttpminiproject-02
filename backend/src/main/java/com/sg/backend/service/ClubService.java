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

import com.sg.backend.models.Club;
import com.sg.backend.repositories.ClubRepository;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

@Service
public class ClubService {

	@Autowired
	private ClubRepository clubRepo;

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

		List<Club> squads = new LinkedList<>();

		try (StringReader strReader = new StringReader(payload)) {
			JsonReader r = Json.createReader(strReader);
			JsonObject j = r.readObject();

			JsonArray squadList = j.getJsonArray("squad");

			System.out.println("Total number of players: " + squadList.size());
			System.out.println("-----------------------------------------------------------");
			// had the jsonvalueimpl cast issue
			for (JsonValue v : j.getJsonArray("squad")) {
				if (v.getValueType() == JsonValue.ValueType.OBJECT) {
					try {
						squads.add(Club.create((JsonObject) v));
					} catch (Exception ex) {
						System.err.printf("Error: %s\n", ex.getMessage());
					}
				}
			}
			// list down all the individual player names and ids respectively
			for (Club club : squads) {
				System.out.println(club.getName() + ": " + club.getId());
				// to store into mySQL
				String playerId = club.getId();
				String playerName = club.getName();
				Club player = new Club();
				player.setId(playerId);
				player.setName(playerName);
				System.out.println(player);
				// check the mySQL db for existing IDs before inserting
				List<String> ids = clubRepo.getPlayerIds();
				if (ids.contains(player.getId())) {
					System.out.println("Player already exists in database.");
					System.out.println("-------------------------------");
					System.out.println("Loading the squad");
					// return squads so it will not be blank on view
					return squads;
				}
				// insert only if id doesnt exist
				clubRepo.insert(player);
			}
		}
		return squads;
	}

}
