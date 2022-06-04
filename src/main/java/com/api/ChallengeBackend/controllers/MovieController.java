package com.api.ChallengeBackend.controllers;

import com.api.ChallengeBackend.dto.MovieDTO;
import com.api.ChallengeBackend.payload.response.MessageResponse;
import com.api.ChallengeBackend.security.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/add")
    ResponseEntity<?> createMovie(@Valid @RequestBody MovieDTO movieDTO) {
        if (movieService.isImage(movieDTO.getImage())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: esta imagen de pelicula ya esta en uso"));
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(movieService.addMovie(movieDTO));
    }
}
