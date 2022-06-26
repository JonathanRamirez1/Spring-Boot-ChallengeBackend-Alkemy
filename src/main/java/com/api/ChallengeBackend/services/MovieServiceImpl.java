package com.api.ChallengeBackend.services;

import com.api.ChallengeBackend.domain.dto.MovieDTO;
import com.api.ChallengeBackend.web.exceptions.ResourceNotFoundException;
import com.api.ChallengeBackend.domain.models.Movie;
import com.api.ChallengeBackend.dao.repository.CharacterRepository;
import com.api.ChallengeBackend.dao.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CharacterRepository characterRepository;

    @Override
    public boolean isImage(String image) {
        return movieRepository.existsByImage(image);
    }

    @Override
    public boolean isTitle(String title) {
        return movieRepository.existsByTitle(title);
    }

    @Override
    public Movie saveMovie(Movie movie) {
        movie = movieRepository.save(movie);
        Movie finalMovie = movie;
        movie.getCharacters().forEach(character -> {
            character.setMovie(finalMovie);
            characterRepository.save(character);
        });
        return movie;
    }

    @Override
    @Transactional(readOnly = true)
    public Movie findMovieById(Integer idMovie) {
        return movieRepository.findById(idMovie)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "idMovie", idMovie));
    }

    @Override
    public List<Movie> findMovies() {
        return movieRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> findMovieByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    @Override
    public List<Movie> orderMovie(String order) {
        if (order.equalsIgnoreCase("ASC")) {
            return movieRepository.findAllByOrderByTitleAsc();
        } else if (order.equalsIgnoreCase("DESC")) {
            return movieRepository.findAllByOrderByTitleDesc();
        } else {
            return movieRepository.findAll();
        }
    }

    @Override
    public Movie updateMovie(MovieDTO movieDTO, Integer idMovie) {
        Movie movie = movieRepository.findById(idMovie)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "idMovie", idMovie));

        movie.setImage(movieDTO.getImage());
        movie.setTitle(movieDTO.getTitle());
        movie.setTimeStamp(movieDTO.getTimeStamp());
        movie.setQualification(movieDTO.getQualification());
        return movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(Integer idMovie) {
        Movie movie = movieRepository.findById(idMovie)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "idMovie", idMovie));
        movieRepository.delete(movie);
    }
}