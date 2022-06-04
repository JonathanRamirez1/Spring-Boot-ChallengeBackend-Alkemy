package com.api.ChallengeBackend.repositories;

import com.api.ChallengeBackend.models.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {

    Boolean existsByImage(String image);
    Boolean existsByName(String name);
    Boolean existsByHistory(String history);
    List<Character> findByName(String name);
    List<Character>  findAllByAge(Integer age);

    @Query("SELECT c FROM Character c WHERE c.name LIKE :name")
    public List<Character> buscarPorNombre(@Param("name") String name);
}
