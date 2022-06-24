package com.api.ChallengeBackend.domain.dto;

import com.api.ChallengeBackend.domain.models.Movie;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CharacterDTO {

    private Long idPersonaje;

    @NotEmpty
    @Size(max = 100)
    private String image;

    @NotEmpty
    @Size(max = 50)
    private String name;

    @NotNull
    private Integer age;

    @NotNull
    private float weight;

    @NotEmpty
    @Size(max = 200)
    private String history;

    private Movie movie;

    public Long getIdPersonaje() {
        return idPersonaje;
    }

    public void setIdPersonaje(Long idPersonaje) {
        this.idPersonaje = idPersonaje;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public CharacterDTO() {
        super();
    }
}