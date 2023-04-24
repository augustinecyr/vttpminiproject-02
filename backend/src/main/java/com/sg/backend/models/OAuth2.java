package com.sg.backend.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class OAuth2 {
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("accessToken", this.accessToken)
                .build();
    }
}
