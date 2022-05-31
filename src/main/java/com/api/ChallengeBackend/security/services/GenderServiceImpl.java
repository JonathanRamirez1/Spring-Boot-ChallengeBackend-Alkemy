package com.api.ChallengeBackend.security.services;

import com.api.ChallengeBackend.dto.GenderDTO;
import com.api.ChallengeBackend.models.Gender;
import com.api.ChallengeBackend.repositories.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenderServiceImpl implements GenderService {

    @Autowired
    private GenderRepository genderRepository;

    @Override
    public boolean isImage(String image) {
        return genderRepository.existsByImage(image);
    }

    @Override
    public Gender addGender(GenderDTO genderDTO) {
        Gender genders = new Gender(
                genderDTO.getName(),
                genderDTO.getImage(),
                genderDTO.getMovies());
        return genderRepository.save(genders);
    }
}
