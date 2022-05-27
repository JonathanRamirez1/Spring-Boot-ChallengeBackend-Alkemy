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
    public ResponseEntity<?> createCharacter(@Valid @RequestBody CharacterDTO characterDTO) {
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

    /**
     * Busca un personaje por id
     **/
    @GetMapping("/get/{idPersonaje}")
    public ResponseEntity<?> readCharacterById(@Valid @PathVariable(value = "idPersonaje") Long idPersonaje) {
        try {
            Character getCharacters = characterService.findCharacterById(idPersonaje);
            return ResponseEntity
                    .ok()
                    .body(getCharacters);
        } catch (Exception exception) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse("El personaje con id " + idPersonaje + " no existe"));
        }
    }

    /**
     * Busca todos los personajes que hay en la base de datos
     **/
    @GetMapping("/get")
    public ResponseEntity<?> readCharacter() {
        return ResponseEntity
                .ok()
                .body(characterService.findCharacters());
    }

    /**
     * Actualiza un personaje por id
     **/
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

    /**
     * Elimina un personaje por id
     **/
    @DeleteMapping("/delete/{idPersonaje}")
    public ResponseEntity<?> deleteCharacter(@PathVariable(value = "idPersonaje") Long idPersonaje) {
        try {
            characterService.deleteCharacter(idPersonaje);
            return ResponseEntity
                    .ok()
                    .body(new MessageResponse("El personaje con id " + idPersonaje + " se ha eliminado"));
        } catch (Exception exception) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse("El personaje con id " + idPersonaje + " no existe"));
        }
    }
}
