package com.api.ChallengeBackend.security.services;

import com.api.ChallengeBackend.dto.MovieDTO;
import com.api.ChallengeBackend.models.Movie;

import java.util.List;

public interface MovieService {

    public boolean isImage(String image);

    public Movie addMovie(MovieDTO movieDTO);
    public List<Movie> findMovies();
}
