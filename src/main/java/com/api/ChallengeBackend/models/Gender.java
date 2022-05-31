package com.api.ChallengeBackend.models;

import lombok.Data;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "genero",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "image")
})
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGender;

    private String name;

    private String image;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "genero_peliculas",
            joinColumns = @JoinColumn(name = "genero_id"),
            inverseJoinColumns = @JoinColumn(name = "pelicula_id"))
    private Set<Movie> movies = new HashSet<>();

    public Gender() {}

    public Gender(String name, String image, Set<Movie> movies) {
        this.name = name;
        this.image = image;
        this.movies = movies;
    }
}
