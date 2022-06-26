package com.api.ChallengeBackend.web.payload.response;

import lombok.Data;
import java.util.Date;

@Data
public class MovieResponse {

    private String image;
    private String title;
    private Date timeStamp;

    public MovieResponse(String image, String title, Date timeStamp) {
        this.image = image;
        this.title = title;
        this.timeStamp = timeStamp;
    }
}