package com.api.ChallengeBackend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;

@Entity
@Table(name = "personaje",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "image"),
        @UniqueConstraint(columnNames = "history")
})
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersonaje;

    private String image;

    private String name;

    private Integer age;

    private float weight;

    private String history;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMovie")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //Ignora la serializacion
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

    public Character() {};

    public Character(String image, String name, Integer age, float weight, String history) {
        this.image = image;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.history = history;
    }
}