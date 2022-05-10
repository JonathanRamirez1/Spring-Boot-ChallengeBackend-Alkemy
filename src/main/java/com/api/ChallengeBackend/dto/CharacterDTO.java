package com.api.ChallengeBackend.dto;

import com.api.ChallengeBackend.models.Character;
import com.api.ChallengeBackend.models.Movie;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
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

    private Set<Movie> movies;

    public CharacterDTO() {
        super();
    }
}
