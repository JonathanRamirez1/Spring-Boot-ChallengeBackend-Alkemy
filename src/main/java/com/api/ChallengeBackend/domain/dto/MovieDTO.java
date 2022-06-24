package com.api.ChallengeBackend.domain.dto;

import com.api.ChallengeBackend.domain.models.Character;
import com.api.ChallengeBackend.domain.models.Gender;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.Set;

public class MovieDTO {

    private Integer idMovie;

    @NotEmpty
    @Size(max = 100)
    private String image;

    @NotEmpty
    @Size(max = 50)
    private String title;

    @NotNull
    private Date timeStamp;

    @NotNull
    @Min(1)
    @Max(5)
    private int qualification;

    private Set<Character> characters;

    private Set<Gender> genders;

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
        this.genders = genders;
    }

    public MovieDTO() {
        super();
    }
}