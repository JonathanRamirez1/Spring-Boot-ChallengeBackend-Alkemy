package com.api.ChallengeBackend.dto;

import lombok.Data;
import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class MovieDTO {

    private Integer idMovie;

    @NotEmpty
    @Size(max = 100)
    @Column(unique = true)
    private String image;

    @NotEmpty
    @Size(max = 50)
    private String title;

    @NotNull
    private Date timeStamp;

    @NotNull
    private int qualification;

    public MovieDTO() {
        super();
    }
}
