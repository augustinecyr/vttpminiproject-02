package com.sg.backend.models;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

public class Fixtures {
    private String fullMatchDate;
    private String matchTime;
    private String homeClubName;
    private String homeClubImage;
    private String awayClubName;
    private String awayClubImage;
    private String result;

    public String getFullMatchDate() {
        return fullMatchDate;
    }

    public void setFullMatchDate(String fullMatchDate) {
        this.fullMatchDate = fullMatchDate;
    }

    public String getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(String matchTime) {
        this.matchTime = matchTime;
    }

    public String getHomeClubName() {
        return homeClubName;
    }

    public void setHomeClubName(String homeClubName) {
        this.homeClubName = homeClubName;
    }

    public String getHomeClubImage() {
        return homeClubImage;
    }

    public void setHomeClubImage(String homeClubImage) {
        this.homeClubImage = homeClubImage;
    }

    public String getAwayClubName() {
        return awayClubName;
    }

    public void setAwayClubName(String awayClubName) {
        this.awayClubName = awayClubName;
    }

    public String getAwayClubImage() {
        return awayClubImage;
    }

    public void setAwayClubImage(String awayClubImage) {
        this.awayClubImage = awayClubImage;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public static Fixtures create(JsonObject json) {
        final Fixtures fixture = new Fixtures();
        fixture.setFullMatchDate(json.getString("fullMatchDate"));
        fixture.setMatchTime(json.getString("matchTime"));
        fixture.setHomeClubName(json.getString("homeClubName"));
        fixture.setHomeClubImage(json.getString("homeClubImage"));
        fixture.setAwayClubName(json.getString("awayClubName"));
        fixture.setAwayClubImage(json.getString("awayClubImage"));
        fixture.setResult(json.getString("result"));
        return fixture;
    }

    public static Fixtures create(String json) {
        try (StringReader strReader = new StringReader(json)) {
            JsonReader j = Json.createReader(strReader);
            return create(j.readObject());
        }
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("fullMatchDate", this.fullMatchDate)
                .add("matchTime", this.matchTime)
                .add("homeClubName", this.homeClubName)
                .add("homeClubImage", this.homeClubImage)
                .add("awayClubName", this.awayClubName)
                .add("awayClubName", this.awayClubImage)
                .add("result", this.result)
                .build();
    }

    public JsonValue toJsonValue() {
        return Json.createObjectBuilder()
                .add("fullMatchDate", this.fullMatchDate)
                .add("matchTime", this.matchTime)
                .add("homeClubName", this.homeClubName)
                .add("homeClubImage", this.homeClubImage)
                .add("awayClubName", this.awayClubName)
                .add("awayClubName", this.awayClubImage)
                .add("result", this.result)
                .build();
    }

    @Override
    public String toString() {
        return "Fixtures [fullMatchDate=" + fullMatchDate + ", matchTime=" + matchTime + ", homeClubName="
                + homeClubName + ", homeClubImage=" + homeClubImage + ", awayClubName=" + awayClubName
                + ", awayClubImage=" + awayClubImage + ", result=" + result + "]";
    }

}
