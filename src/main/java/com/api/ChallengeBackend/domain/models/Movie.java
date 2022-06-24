package com.api.ChallengeBackend.domain.models;

import com.fasterxml.jackson.annotation.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "pelicula",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "image"),
        @UniqueConstraint(columnNames = "title")
})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idMovie")
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMovie;
    private String image;
    private String title;
    private Date timeStamp;
    private int qualification;

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Character> characters = new HashSet<>();

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Gender> genders = new HashSet<>();

    public Integer getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(Integer idMovie) {
        this.idMovie = idMovie;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getQualification() {
        return qualification;
    }

    public void setQualification(int qualification) {
        this.qualification = qualification;
    }

    public Set<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(Set<Character> characters) {
        this.characters = characters;
    }

    public Set<Gender> getGenders() {
        return genders;
    }

    public void setGenders(Set<Gender> genders) {
        this.genders.clear();
        this.genders.addAll(genders);
    }

    public Movie() {}

    public Movie(String image, String title, Date timeStamp, int qualification, Set<Character> characters) {
        this.image = image;
        this.title = title;
        this.timeStamp = timeStamp;
        this.qualification = qualification;
        this.characters = characters;
    }
}