package com.api.ChallengeBackend.repositories;

import com.api.ChallengeBackend.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    Boolean existsByImage(String image);
}
