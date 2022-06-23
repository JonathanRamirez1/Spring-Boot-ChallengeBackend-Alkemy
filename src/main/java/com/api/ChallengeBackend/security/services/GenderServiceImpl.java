package com.api.ChallengeBackend.security.services;

import com.api.ChallengeBackend.dto.GenderDTO;
import com.api.ChallengeBackend.exceptions.ResourceNotFoundException;
import com.api.ChallengeBackend.models.Gender;
import com.api.ChallengeBackend.models.Movie;
import com.api.ChallengeBackend.repositories.GenderRepository;
import com.api.ChallengeBackend.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenderServiceImpl implements GenderService {

    @Autowired
    private GenderRepository genderRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public boolean isName(String name) {
        return genderRepository.existsByName(name);
    }

    @Override
    public boolean isImage(String image) {
        return genderRepository.existsByImage(image);
    }

    @Override
    public Gender addGender(Integer idMovie, GenderDTO genderDTO) {
        Gender genders = new Gender(
                genderDTO.getName(),
                genderDTO.getImage());

        Movie movie = movieRepository.findById(idMovie)
                .orElseThrow(() -> new ResourceNotFoundException("pelicula", "idMovie", idMovie));
        genders.setMovie(movie);
        return genderRepository.save(genders);
    }
}