package com.api.ChallengeBackend.security.services;

import com.api.ChallengeBackend.dto.CharacterDTO;
import com.api.ChallengeBackend.exceptions.ResourceNotFoundException;
import com.api.ChallengeBackend.models.Character;
import com.api.ChallengeBackend.repositories.CharacterRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {

    private ModelMapper modelMapper;

    @Autowired
    CharacterRepository characterRepository;

    @Override
    public boolean isImage(String image) {
        return characterRepository.existsByImage(image);
    }

    @Override
    public boolean isName(String name) {
        return characterRepository.existsByName(name);
    }

    @Override
    public boolean isHistory(String history) {
        return characterRepository.existsByHistory(history);
    }

    @Override
    public Character addCharacter(CharacterDTO characterDTO) {
        Character characters = new Character(
                characterDTO.getImage(),
                characterDTO.getName(),
                characterDTO.getAge(),
                characterDTO.getWeight(),
                characterDTO.getHistory(),
                characterDTO.getMovies());
        return characterRepository.save(characters);
    }

    @Override
    @Transactional(readOnly = true)
    public Character findCharacterById(Long idPersonaje) {
        return characterRepository.findById(idPersonaje)
                .orElseThrow(() -> new ResourceNotFoundException("Character", "idPersonaje", idPersonaje));
    }

    @Override
    public List<Character> findCharacters() {
        return characterRepository.findAll();
    }

    @Override
    public Character updateCharacter(CharacterDTO characterDTO, Long idPersonaje) {
        Character character = characterRepository.findById(idPersonaje)
                .orElseThrow(() -> new ResourceNotFoundException("Character", "idPersonaje", idPersonaje));

        character.setImage(characterDTO.getImage());
        character.setName(characterDTO.getName());
        character.setAge(characterDTO.getAge());
        character.setWeight(characterDTO.getWeight());
        character.setHistory(character.getHistory());
        // character.setMovies(character.getMovies());
        return characterRepository.save(character);
    }

    @Override
    public void deleteCharacter(Long idPersonaje) {
        Character character = characterRepository.findById(idPersonaje)
                .orElseThrow(() -> new ResourceNotFoundException("Character", "idPersonaje", idPersonaje));
        characterRepository.delete(character);
    }

    // Convierte entidad a DTO
    private CharacterDTO mapDTO(Character character) {
        return modelMapper.map(character, CharacterDTO.class);
    }

    // Convierte de DTO a Entidad
    private Character mapEntity(CharacterDTO characterDTO) {
        return modelMapper.map(characterDTO, Character.class);
    }
}
