package com.api.ChallengeBackend.models;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "pelicula",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "image"),
                @UniqueConstraint(columnNames = "title")
})
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMovie;

    @NotBlank
    private String image;

    @NotBlank
    private String title;

    @NotNull
    private Date timeStamp;

    @NotNull
    private int qualification;

    public Movie() {}

    public Movie(String image, String title, Date timeStamp, int qualification) {
        this.image = image;
        this.title = title;
        this.timeStamp = timeStamp;
        this.qualification = qualification;
    }
}
