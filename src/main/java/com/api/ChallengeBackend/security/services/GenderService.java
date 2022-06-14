package com.api.ChallengeBackend.security.services;

import com.api.ChallengeBackend.dto.GenderDTO;
import com.api.ChallengeBackend.models.Gender;

public interface GenderService {

    public boolean isName(String name);
    public boolean isImage(String image);
    public Gender addGender(Integer idMovie, GenderDTO genderDTO);
}
