package com.api.ChallengeBackend.security.services;

import com.api.ChallengeBackend.dto.CharacterDTO;
import com.api.ChallengeBackend.models.Character;

import java.util.List;

public interface CharacterService {

    public boolean isImage(String image);
    public boolean isName(String name);
    public boolean isHistory(String history);

    public Character addCharacter(CharacterDTO characterDTO);
    public Character findCharacterById(Long idPersonaje);
    public List<Character> findCharacters();
    public Character updateCharacter(CharacterDTO characterDTO, Long idPersonaje);
    public void deleteCharacter(Long idPersonaje);
}
