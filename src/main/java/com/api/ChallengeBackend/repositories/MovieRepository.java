package com.api.ChallengeBackend.repositories;

import com.api.ChallengeBackend.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    Boolean existsByImage(String image);
    Boolean existsByTitle(String title);

    List<Movie> findByTitle(String title);
    List<Movie> findAllByOrderByTitleAsc();
    List<Movie> findAllByOrderByTitleDesc();
}
