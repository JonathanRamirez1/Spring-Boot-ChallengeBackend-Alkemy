package com.api.ChallengeBackend.security.services;

import com.api.ChallengeBackend.dto.MovieDTO;
import com.api.ChallengeBackend.models.Movie;
import java.util.List;

public interface MovieService {

    public boolean isImage(String image);
    public boolean isTitle(String title);

    public Movie saveMovie(Movie movie);
    public Movie findMovieById(Integer idMovie);
    public List<Movie> findMovies();
    public List<Movie> findMovieByTitle(String title);
    public List<Movie> orderMovie(String order);
    public Movie updateMovie(MovieDTO movieDTO, Integer idMovie);
    public void deleteMovie(Integer idMovie);
}
