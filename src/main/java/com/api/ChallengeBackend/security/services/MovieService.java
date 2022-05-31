package com.api.ChallengeBackend.security.services;

import com.api.ChallengeBackend.models.Movie;

import java.util.List;

public interface MovieService {

    public List<Movie> findMovies();
}
