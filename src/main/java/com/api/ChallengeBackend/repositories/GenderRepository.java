package com.api.ChallengeBackend.repositories;

import com.api.ChallengeBackend.models.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository extends JpaRepository<Gender, Long> {

    Boolean existsByName(String name);
    Boolean existsByImage(String image);
}
