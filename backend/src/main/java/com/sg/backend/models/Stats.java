package com.sg.backend.models;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Stats {
    private int yellowCards;
    private int redCards;
    private int goals;
    private int assists;
    private int cleanSheets;
    private int concededGoals;
    private boolean isGoalkeeper;

    public int getYellowCards() {
        return yellowCards;
    }

    public void setYellowCards(int yellowCards) {
        this.yellowCards = yellowCards;
    }

    public int getRedCards() {
        return redCards;
    }

    public void setRedCards(int redCards) {
        this.redCards = redCards;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getCleanSheets() {
        return cleanSheets;
    }

    public void setCleanSheets(int cleanSheets) {
        this.cleanSheets = cleanSheets;
    }

    public int getConcededGoals() {
        return concededGoals;
    }

    public void setConcededGoals(int concededGoals) {
        this.concededGoals = concededGoals;
    }

    public boolean isGoalkeeper() {
        return isGoalkeeper;
    }

    public void setGoalkeeper(boolean isGoalkeeper) {
        this.isGoalkeeper = isGoalkeeper;
    }

     
    public static Stats create(JsonObject performance) {
        final Stats stat = new Stats();
        stat.setYellowCards(performance.getInt("yellowCards"));
        stat.setAssists(performance.getInt("assists"));
        stat.setGoals(performance.getInt("goals"));
        stat.setCleanSheets(performance.getInt("toNil"));
        stat.setRedCards(performance.getInt("redCards"));
        stat.setGoalkeeper(performance.getBoolean("isGoalkeeper"));
        return stat;
    }
    

    public static Stats create(int yellowCards, int redCards, int goals, int assists, int cleanSheets,
            int concededGoals, boolean isGoalkeeper) {
        final Stats stat = new Stats();
        stat.setYellowCards(yellowCards);
        stat.setRedCards(redCards);
        stat.setGoals(goals);
        stat.setAssists(assists);
        stat.setCleanSheets(cleanSheets);
        stat.setConcededGoals(concededGoals);
        stat.setGoalkeeper(isGoalkeeper);
        return stat;
    }

    public static Stats create(String json) {
        try (StringReader strReader = new StringReader(json)) {
            JsonReader j = Json.createReader(strReader);
            return create(j.readObject());
        }
    }

    @Override
    public String toString() {
        return "Stats [yellowCards=" + yellowCards + ", redCards=" + redCards + ", goals=" + goals + ", assists="
                + assists + ", cleanSheets=" + cleanSheets + ", concededGoals=" + concededGoals + ", isGoalkeeper="
                + isGoalkeeper + "]";
    }

}
