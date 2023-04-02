package com.sg.backend.models;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

public class Club {

    private String name;
    private String id;
    private String heroImage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeroImage() {
        return heroImage;
    }

    public void setHeroImage(String heroImage) {
        this.heroImage = heroImage;
    }

    public static Club create(JsonObject json) {
        final Club squad = new Club();
        squad.setName(json.getString("name"));
        squad.setId(json.getString("id"));
        squad.setHeroImage(json.getString("heroImage"));
        return squad;
    }

    public static Club create(String json) {
        try (StringReader strReader = new StringReader(json)) {
            JsonReader j = Json.createReader(strReader);
            return create(j.readObject());
        }
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("id", this.id.toString())
                .add("name", this.name)
                .add("heroImage", this.heroImage)
                .build();
    }

    public JsonValue toJsonValue() {
        return Json.createObjectBuilder()
                .add("id", this.id.toString())
                .add("name", this.name)
                .add("heroImage", this.heroImage)
                .build();
    }

    @Override
    public String toString() {
        return "Club [name=" + name + ", id=" + id + ", heroImage=" + heroImage + "]";
    }

}
