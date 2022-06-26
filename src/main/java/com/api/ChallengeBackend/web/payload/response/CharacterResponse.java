package com.api.ChallengeBackend.web.payload.response;

import lombok.Data;

@Data
public class CharacterResponse {

    private String name;
    private String image;

    public CharacterResponse(String name, String image) {
        this.name = name;
        this.image = image;
    }
}