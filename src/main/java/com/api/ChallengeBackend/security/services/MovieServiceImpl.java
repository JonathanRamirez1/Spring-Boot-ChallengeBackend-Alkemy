package com.api.ChallengeBackend.security.services;

import com.api.ChallengeBackend.dto.MovieDTO;
import com.api.ChallengeBackend.models.Movie;
import com.api.ChallengeBackend.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public boolean isImage(String image) {
        return movieRepository.existsByImage(image);
    }

    @Override
    public Movie addMovie(MovieDTO movieDTO) {
       Movie movie = new Movie(
               movieDTO.getImage(),
               movieDTO.getTitle(),
               movieDTO.getTimeStamp(),
               movieDTO.getQualification());
       return movieRepository.save(movie);
    }

    @Override
    public List<Movie> findMovies() {
        return movieRepository.findAll();
    }
}
