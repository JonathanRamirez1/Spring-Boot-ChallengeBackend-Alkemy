package com.api.ChallengeBackend.web.controllers;

import com.api.ChallengeBackend.domain.dto.CharacterDTO;
import com.api.ChallengeBackend.domain.models.Character;
import com.api.ChallengeBackend.domain.models.Movie;
import com.api.ChallengeBackend.web.payload.response.CharacterResponse;
import com.api.ChallengeBackend.web.payload.response.MessageResponse;
import com.api.ChallengeBackend.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @Autowired
    HttpServletRequest requestCharacter;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/movies/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<?> createCharacter(@PathVariable(value = "idMovie") Integer idMovie, @Valid @RequestBody CharacterDTO characterDTO) {
        if (characterService.isImage(characterDTO.getImage())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: La imagen de Personaje ya esta en uso"));
        }
        if (characterService.isHistory(characterDTO.getHistory())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: La historia de Personaje ya esta en uso"));
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(characterService.addCharacter(idMovie, characterDTO));
    }

    /**
     * Muestra el detalle del personaje
     **/
    @GetMapping("/details")
    public ResponseEntity<?> readCharacterById(@Valid @RequestParam(required = false, value = "idCharacter") Long idPersonaje) {
        try {
            /**
             * Busca un personaje por id
             **/
            if (idPersonaje != null) {
                Character getCharacters = characterService.findCharacterById(idPersonaje);
                return ResponseEntity
                        .ok()
                        .body(getCharacters);
            } else {
                /**
                 * Busca todos los personajes que hay en la base de datos
                 **/
                return ResponseEntity
                        .ok()
                        .body(characterService.findCharacters());
            }
        } catch (Exception exception) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse("El personaje con id " + idPersonaje + " no existe"));
        }
    }

    @GetMapping
    public ResponseEntity<?> filtersForCharacters(@Valid @RequestParam(required = false, value = "name") String name,
                                                  @Valid @RequestParam(required = false, value = "age") Integer age,
                                                  @Valid @RequestParam(required = false, value = "idMovie") Integer idMovie) {

        if (requestCharacter.getQueryString() != null) {
            if (name != null) {
                return getCharacterByName(name);
            } else if (age != null) {
                return getCharacterByAge(age);
            } else if (idMovie != null) {
                return getCharacterByMovies(idMovie);
            } else {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Verifique que la solicitud este bien"));
            }
        }
        return ResponseEntity
                .ok()
                .body(characterService.findCharacters());
    }

    /**
     * Busca un personaje por nombre
     **/
    public ResponseEntity<?> getCharacterByName(String name) {
        List<Character> characterByName = characterService.findCharacterByName(name);
        List<CharacterResponse> characterResponses = new ArrayList<>();

        for (Character characterTemp : characterByName) {
            CharacterResponse characterResponse = new CharacterResponse(characterTemp.getName(), characterTemp.getImage());
            characterResponses.add(characterResponse);
        }

        if (!characterByName.isEmpty()) {
            return ResponseEntity
                    .ok()
                    .body(characterResponses);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse("Error: El personaje con nombre " + name + " no existe"));
        }
    }

    /**
     * Busca un personaje por edad
     **/
    public ResponseEntity<?> getCharacterByAge(Integer age) {
        List<Character> characterByAge = characterService.findCharacterByAge(age);
        List<CharacterResponse> characterResponses = new ArrayList<>();

        for (Character characterTemp : characterByAge) {
            CharacterResponse characterResponse = new CharacterResponse(characterTemp.getName(), characterTemp.getImage());
            characterResponses.add(characterResponse);
        }

        if (!characterByAge.isEmpty()) {
            return ResponseEntity
                    .ok()
                    .body(characterResponses);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse("El personaje con edad " + age + " no existe"));
        }
    }

    /**
     * Busca un personaje por id de pelicula
     **/
    public ResponseEntity<?> getCharacterByMovies(Integer idMovie) {
        List<Character> characterList = characterService.findCharacters();//Se traen todos los personajes
        List<CharacterResponse> charactersMovie = new ArrayList<>(); //Se crea un lista para llenarla y luego se retorna
        for (Character characterTemp : characterList) {
            Movie moviesCharacter = characterTemp.getMovie(); //Cuando se recorre la lista de personajes, se le obtiene su lista de peliculas internas

            if (Objects.equals(moviesCharacter.getIdMovie(), idMovie)) {
                //Si se encuentra un id de pelicula que coincida con el id que llego se agrega

                //Se crea el nuevo objecto para agregarlo a la lista
                CharacterResponse personajePulido = new CharacterResponse(characterTemp.getName(), characterTemp.getImage());
                charactersMovie.add(personajePulido);
            }
        }

        if (!characterList.isEmpty()) {
            return ResponseEntity
                    .ok()
                    .body(charactersMovie);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse("El personaje con id de pelicula " + idMovie + " no existe"));
        }
    }

    /**
     * Actualiza un personaje por id
     **/
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{idCharacter}")
    public ResponseEntity<?> updateCharacter(@Valid @RequestBody CharacterDTO characterDTO, @PathVariable(value = "idCharacter") Long idPersonaje) {
        try {
            Character characterResponse = characterService.updateCharacter(characterDTO, idPersonaje);
            return ResponseEntity
                    .ok()
                    .body(characterResponse);

        } catch (Exception exception) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse("El personaje con id " + idPersonaje + " no existe para ser actualizado"));
        }
    }

    /**
     * Elimina un personaje por id, sin eliminar su pelicula relacionada
     **/
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/movies/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<?> deleteCharacter(@PathVariable(value = "idMovie") Integer idMovie, @PathVariable(value = "idCharacter") Long idPersonaje) {
        try {
            characterService.deleteCharacter(idMovie, idPersonaje);
            return ResponseEntity
                    .ok()
                    .body(new MessageResponse("Correcto: El personaje con id " + idPersonaje + " se ha eliminado"));
        } catch (Exception exception) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse("Error: El personaje con id " + idPersonaje + " no existe"));
        }
    }
}