package com.api.ChallengeBackend.payload.response;

import lombok.Data;

@Data
public class CharacterImgNameResponse {

    private String name;
    private String image;

    public CharacterImgNameResponse(String name, String image) {
        this.name = name;
        this.image = image;
    }
}
