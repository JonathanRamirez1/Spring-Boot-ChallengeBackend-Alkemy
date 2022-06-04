package com.api.ChallengeBackend.controllers;

import com.api.ChallengeBackend.dto.CharacterDTO;
import com.api.ChallengeBackend.models.Character;
import com.api.ChallengeBackend.models.Movie;
import com.api.ChallengeBackend.payload.response.CharacterImgNameResponse;
import com.api.ChallengeBackend.payload.response.MessageResponse;
import com.api.ChallengeBackend.security.services.CharacterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @Autowired
    HttpServletRequest request;

    private static final Logger logger = LoggerFactory.getLogger(CharacterController.class);

    @PostMapping("/add")
    public ResponseEntity<?> createCharacter(@Valid @RequestBody CharacterDTO characterDTO) {
      /*  if (characterService.isImage(characterDTO.getImage())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: La imagen de Personaje ya esta en uso"));
        }
        if (characterService.isName(characterDTO.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: El nombre de Personaje ya esta en uso"));
        }
        if (characterService.isHistory(characterDTO.getHistory())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: La historia de Personaje ya esta en uso"));
        }*/
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
     * Muestra el detalle del personaje
     **/
    @GetMapping("/detail")
    public ResponseEntity<?> readCharacterById(@Valid @RequestParam(required = false, value = "idPersonaje") Long idPersonaje) {
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

        if (request.getQueryString() != null) {
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
        List<CharacterImgNameResponse> characterResponses = new ArrayList<>();

        for (Character characterTemp : characterByName) {
            CharacterImgNameResponse characterImgNameResponse = new CharacterImgNameResponse(characterTemp.getName(), characterTemp.getImage());
            characterResponses.add(characterImgNameResponse);
        }

        if (!characterByName.isEmpty()) {
            return ResponseEntity
                    .ok()
                    .body(characterResponses);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse("El personaje con nombre " + name + " no existe"));
        }
    }


    /**
     * Busca un personaje por edad
     **/
    public ResponseEntity<?> getCharacterByAge(Integer age) {
        List<Character> characterByAge = characterService.findCharacterByAge(age);
        List<CharacterImgNameResponse> characterResponses = new ArrayList<>();

        for (Character characterTemp : characterByAge) {
            CharacterImgNameResponse characterImgNameResponse = new CharacterImgNameResponse(characterTemp.getName(), characterTemp.getImage());
            characterResponses.add(characterImgNameResponse);
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
        List<CharacterImgNameResponse> charactersMovie = new ArrayList<>(); //Se crea un lista para llenarla y luego se retorna
        for (Character characterTemp : characterList) {
            Set<Movie> moviesCharacter = characterTemp.getMovies(); //Cuando se recorre la lista de personajes, se le obtiene su lista de peliculas internas

            for (Movie movie : moviesCharacter) {
                if (Objects.equals(movie.getIdMovie(), idMovie)) {
                    //Si se encuentra un id de pelicula que coincida con el id que llego se agrega

                    //Se crea el nuevo objecto para agregarlo a la lista
                    CharacterImgNameResponse personajePulido = new CharacterImgNameResponse(characterTemp.getName(), characterTemp.getImage());
                    charactersMovie.add(personajePulido);
                }
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