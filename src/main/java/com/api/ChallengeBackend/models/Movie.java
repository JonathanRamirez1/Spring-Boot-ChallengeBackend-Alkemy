package com.api.ChallengeBackend.models;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "pelicula")
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMovie;

    private String image;

    private String title;

    private Date timeStamp;

    private int qualification;

    public Movie() {}

    public Movie(String image, String title, Date timeStamp, int qualification) {
        this.image = image;
        this.title = title;
        this.timeStamp = timeStamp;
        this.qualification = qualification;
    }
}
