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
		}
		return squads;
	}

}
