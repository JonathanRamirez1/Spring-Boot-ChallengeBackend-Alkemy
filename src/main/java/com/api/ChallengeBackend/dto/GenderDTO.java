package com.api.ChallengeBackend.dto;

import com.api.ChallengeBackend.models.Movie;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class GenderDTO {

    private Long idGender;

    @NotEmpty
    @Size(max = 50)
    private String name;

    @NotEmpty
    @Size(max = 100)
    private String image;

    private Set<Movie> movies;

    public GenderDTO() {
        super();
    }
}
