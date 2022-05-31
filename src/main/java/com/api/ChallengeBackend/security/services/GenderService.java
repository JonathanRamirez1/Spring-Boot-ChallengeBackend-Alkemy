package com.api.ChallengeBackend.security.services;

import com.api.ChallengeBackend.dto.GenderDTO;
import com.api.ChallengeBackend.models.Gender;

public interface GenderService {

    public boolean isImage(String image);
    public Gender addGender(GenderDTO genderDTO);
}
