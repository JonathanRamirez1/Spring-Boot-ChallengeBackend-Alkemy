package com.api.ChallengeBackend.services;

import com.api.ChallengeBackend.domain.dto.CharacterDTO;
import com.api.ChallengeBackend.domain.models.Character;
import java.util.List;

public interface CharacterService {

    public boolean isImage(String image);
    public boolean isHistory(String history);

    public Character addCharacter(Integer idMovie, CharacterDTO characterDTO);
    public Character findCharacterById(Long idPersonaje);
    public List<Character> findCharacters();
    public List<Character> findCharacterByName(String name);
    public List<Character> findCharacterByAge(Integer age);
    public Character updateCharacter(CharacterDTO characterDTO, Long idPersonaje);
    public void deleteCharacter(Integer idMovie, Long idPersonaje);
}