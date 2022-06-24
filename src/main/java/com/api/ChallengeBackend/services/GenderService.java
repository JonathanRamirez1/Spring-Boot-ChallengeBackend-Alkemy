package com.api.ChallengeBackend.services;

import com.api.ChallengeBackend.domain.dto.GenderDTO;
import com.api.ChallengeBackend.domain.models.Gender;

public interface GenderService {

    public boolean isName(String name);
    public boolean isImage(String image);
    public Gender addGender(Integer idMovie, GenderDTO genderDTO);
}
