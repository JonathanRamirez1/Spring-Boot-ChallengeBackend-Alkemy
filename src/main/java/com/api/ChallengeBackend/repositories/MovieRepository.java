package com.api.ChallengeBackend.repositories;

import com.api.ChallengeBackend.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
