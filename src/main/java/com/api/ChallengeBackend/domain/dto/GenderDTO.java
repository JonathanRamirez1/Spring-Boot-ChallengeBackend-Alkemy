package com.api.ChallengeBackend.domain.dto;

import com.api.ChallengeBackend.domain.models.Movie;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class GenderDTO {

    private Long idGender;

    @NotEmpty
    @Size(max = 50)
    private String name;

    @NotEmpty
    @Size(max = 100)
    private String image;

    private Movie movie;

    public Long getIdGender() {
        return idGender;
    }

    public void setIdGender(Long idGender) {
        this.idGender = idGender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public GenderDTO() {
        super();
    }
}