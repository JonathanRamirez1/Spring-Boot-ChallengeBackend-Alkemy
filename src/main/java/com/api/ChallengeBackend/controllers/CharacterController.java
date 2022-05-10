package com.api.ChallengeBackend.controllers;

import com.api.ChallengeBackend.dto.CharacterDTO;
import com.api.ChallengeBackend.models.Character;
import com.api.ChallengeBackend.payload.response.MessageResponse;
import com.api.ChallengeBackend.security.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/home")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @PostMapping("/add")
    public ResponseEntity<?> CreateCharacter(@Valid @RequestBody CharacterDTO characterDTO) {
        if (characterService.isImage(characterDTO.getImage())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: esta imagen ya esta en uso"));
        }
        if (characterService.isName(characterDTO.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: esta nombre ya esta en uso"));
        }
        if (characterService.isHistory(characterDTO.getHistory())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: esta historia ya esta en uso"));
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(characterService.addCharacter(characterDTO));

       /* if (addRequest.getAge() >= 1 && addRequest.getAge() <= 5) {
           characterRepository.save(characters);
            return ResponseEntity
                    .ok()
                    .body(characters);
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("La calificacion debe estar en un rango de 1 a 5"));
        }*/
    }

    @PutMapping("/update/{idPersonaje}")
    public ResponseEntity<?> updateCharacter(@Valid @RequestBody CharacterDTO characterDTO, @PathVariable(value = "idPersonaje") Long idPersonaje) {
        if (characterService.isImage(characterDTO.getImage())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: es la misma imagen"));
        }
        if (characterService.isName(characterDTO.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: es el mismo nombre"));
        }
        if (characterService.isHistory(characterDTO.getHistory())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: es la misma historia"));
        }

        //Actualiza los datos en la base de datos
        Character characterResponse = characterService.updateCharacter(characterDTO, idPersonaje);
        return ResponseEntity
                .ok()
                .body(characterResponse);
    }

    @DeleteMapping("/delete/{idPersonaje}") //TODO VERIFICAR SI UN ID NO EXISTE DEVUELVA UNA RESPUESTA
    public ResponseEntity<?> deleteCharacter(@PathVariable(value = "idPersonaje") Long idPersonaje) {
        characterService.deleteCharacter(idPersonaje);
        return ResponseEntity
                .ok()
                .body(new MessageResponse("El personaje con id: " + idPersonaje + " se a eliminado"));
    }
}

/*
    public ResponseEntity<?> deleteCharacter(@PathVariable(value = "idPersonaje") Character character) {
        if (!characterRepository.findByIdPersonaje(character.getIdPersonaje())) {
            characterService.deleteCharacter(character.getIdPersonaje());
            return ResponseEntity
                    .ok()
                    .body(new MessageResponse("El personaje con id " + character.getIdPersonaje() + " se a eliminado"));
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("El personaje no existe"));
        }
    }*/

/*@PostMapping("/add")
    public ResponseEntity<?> addCharacter(@Valid @RequestBody CharacterDTO characterDTO) {
        if (characterRepository.existsByImage(characterDTO.getImage())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: esta imagen ya esta en uso"));
        }
        if (characterRepository.existsByName(characterDTO.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: esta nombre ya esta en uso"));
        }
        if (characterRepository.existsByHistory(characterDTO.getHistory())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: esta historia ya esta en uso"));
        }

        //Guarda los datos en la base de datos
       /* Character characters = new Character(
                addRequest.getImage(),
                addRequest.getName(),
                addRequest.getAge(),
                addRequest.getWeight(),
                addRequest.getHistory(),
                addRequest.getMovies());

 characterRepository.save(characters);
        return ResponseEntity
                .ok()
                .body(characters);*/
