package com.api.ChallengeBackend.models;

import lombok.Data;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "personaje",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "image"),
        @UniqueConstraint(columnNames = "name"),
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "personaje_peliculas",
            joinColumns = @JoinColumn(name = "personaje_id"),
            inverseJoinColumns = @JoinColumn(name = "pelicula_id"))
    private Set<Movie> movies = new HashSet<>();

    public Character() {}

    public Character(String image, String name, Integer age, float weight, String history, Set<Movie> movies) {
        this.image = image;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.history = history;
        this.movies = movies;
    }
}
