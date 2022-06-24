package com.api.ChallengeBackend.services;

import com.api.ChallengeBackend.domain.dto.GenderDTO;
import com.api.ChallengeBackend.web.exceptions.ResourceNotFoundException;
import com.api.ChallengeBackend.domain.models.Gender;
import com.api.ChallengeBackend.domain.models.Movie;
import com.api.ChallengeBackend.dao.repository.GenderRepository;
import com.api.ChallengeBackend.dao.repository.MovieRepository;
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