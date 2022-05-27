package com.api.ChallengeBackend.repositories;

import com.api.ChallengeBackend.models.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, Long> {

    Boolean existsByImage(String image);
    Boolean existsByName(String name);
    Boolean existsByHistory(String history);
}
